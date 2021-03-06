package com.dynamicdevs.mvvmgroupapi.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dynamicdevs.mvvmgroupapi.databinding.CardDisplayFragmentBinding
import com.dynamicdevs.mvvmgroupapi.databinding.CardItemViewBinding
import com.dynamicdevs.mvvmgroupapi.databinding.FavCardItemViewBinding
import com.dynamicdevs.mvvmgroupapi.model.PokeCard
import com.dynamicdevs.mvvmgroupapi.model.data.Result
import com.dynamicdevs.mvvmgroupapi.view.adapter.PokeAdapter.*

class PokeAdapter(private val delegate: PokeDelegate) : RecyclerView.Adapter<PokeViewHolder>() {

    private lateinit var pokeCard: PokeCard

    interface PokeDelegate {
        fun selectCard(pokeCard: PokeCard)
    }



    inner class PokeViewHolder(val binding: CardItemViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    var pokes: List<Result> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeViewHolder {
        val binding = CardItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PokeViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PokeViewHolder, position: Int) {

        with(pokes[position]) {
            holder.binding.apply {
                Glide.with(holder.itemView)
                    .applyDefaultRequestOptions(RequestOptions.fitCenterTransform())
                    .load(images.large)
                    .into(holder.binding.posterImageview)
                holder.binding.nameTextview.text = pokes[position].name
                holder.binding.setidTextview.text = pokes[position].set.id
                holder.binding.cardnumTextview.text = pokes[position].number

                holder.binding.posterImageview.setOnClickListener {
                    pokeCard = PokeCard(
                        set.id,
                        name,
                        nationalPokedexNumbers.first(),
                        images.large
                    )
                    delegate.selectCard(pokeCard)
                    Log.d("TAG_X", "Clicked on item.")

                }


            }
        }
    }

    override fun getItemCount() = pokes.size


}