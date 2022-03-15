package com.therealsanjeev.growwgame.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.therealsanjeev.growwgame.databinding.ItemCardBinding

class CardsAdapter(): RecyclerView.Adapter<CardsAdapter.CardViewHolder>() {

    var onClickCard: ((item: Int,binding: ItemCardBinding) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):CardViewHolder {
        return CardViewHolder(ItemCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.onClickCard=onClickCard
        holder.bind(position)
    }

    override fun getItemCount(): Int =9


    inner class CardViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var onClickCard: ((item: Int,binding: ItemCardBinding) -> Unit)? = null

        fun bind(pos: Int) {
            binding.apply {

                root.setOnClickListener {
                    onClickCard?.invoke(pos,binding)
                }

            }
        }
    }
}