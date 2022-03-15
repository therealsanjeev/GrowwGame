package com.therealsanjeev.growwgame.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.therealsanjeev.growwgame.activities.MainActivity
import com.therealsanjeev.growwgame.R
import com.therealsanjeev.growwgame.adapter.CardsAdapter
import com.therealsanjeev.growwgame.databinding.FragmentEndGameBinding
import com.therealsanjeev.growwgame.databinding.ItemCardBinding
import com.therealsanjeev.growwgame.utils.Constants.USER_INPUT
import com.therealsanjeev.growwgame.utils.show
import kotlinx.coroutines.*
import kotlin.random.Random

class EndGameFragment : Fragment() {

    private val binding:FragmentEndGameBinding by lazy {
        FragmentEndGameBinding.inflate(layoutInflater)
    }
    private val cardsAdapter: CardsAdapter by lazy {
        CardsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }


    var userInput=0
    val setUserInput= Random.nextInt(0, 10)
    var attempts=2

    val scope = CoroutineScope(Job() + Dispatchers.Main)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userInput= arguments?.getInt(USER_INPUT)!!

        binding.apply {
            rvCards.apply {
                adapter= cardsAdapter
                layoutManager=GridLayoutManager(requireActivity(),3)
            }
        }

        init()
    }

    private fun init(){
        cardsAdapter.onClickCard={ pos:Int,binding: ItemCardBinding->
            this.binding.tvAttempts.text="$attempts Attempts left"
            if(pos==setUserInput){
                binding.apply {
                    tvResult.text="You Won!\nIt's $userInput"

                    clCard.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                }

                this.binding.laWellDone.show()

                scope.launch {
                    delay(3000)
                    showWinDialog()
                }


            }else{
                binding.apply {
                    tvResult.text="Try Again:("

                    clCard.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.red
                        )
                    )
                }
            }

            if(attempts==0){
                this.binding.rvCards.adapter=cardsAdapter
                attempts=3
                this.binding.tvAttempts.text="$attempts Attempts left"
                showLoseDialog()
            }
            attempts--

        }
    }


    private fun showLoseDialog() {
        AlertDialog.Builder(requireContext()).setCancelable(false)
            .setTitle("You Lost")
            .setNeutralButton("Retry") { dialogInterface, _ ->
                dialogInterface.dismiss()

            }
            .create().show()
    }

    private fun showWinDialog() {
        AlertDialog.Builder(requireContext()).setCancelable(false)
            .setTitle("You Win")
            .setNeutralButton("Play Again") { dialogInterface, _ ->
                dialogInterface.dismiss()
                startActivity(Intent( requireActivity(), MainActivity::class.java))
                requireActivity().finish()

            }
            .create().show()
    }

}