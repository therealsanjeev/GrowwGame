package com.therealsanjeev.growwgame.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.therealsanjeev.growwgame.R
import com.therealsanjeev.growwgame.databinding.FragmentStartGameBinding
import com.therealsanjeev.growwgame.utils.Constants.USER_INPUT


class StartGameFragment : Fragment() {

    private val binding:FragmentStartGameBinding by lazy {
        FragmentStartGameBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init(){
        binding.apply {
            npSelectNumber.apply {
                minValue=1
                maxValue=10
            }

            btStartGame.setOnClickListener {
                val fr: FragmentTransaction = requireFragmentManager().beginTransaction()
                val bundle = Bundle()
                bundle.putInt(USER_INPUT, npSelectNumber.value)
                val frag=EndGameFragment()
                frag.arguments=bundle
                fr.replace(R.id.fragmentContainer, frag)
                fr.commit()
            }
        }
    }





}