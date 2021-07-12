package com.dynamicdevs.mvvmgroupapi.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dynamicdevs.mvvmgroupapi.databinding.FavCardItemViewBinding
import com.dynamicdevs.mvvmgroupapi.model.PokeCard

class FavoriteAdapter(private val delegate: FavoriteAdapter.DeleteDeletage) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    inner class FavoriteViewHolder(val binding: FavCardItemViewBinding):
        RecyclerView.ViewHolder(binding.root)

    interface DeleteDeletage {
        fun selectCard(pokeCard: PokeCard)
    }

    var list: List<PokeCard> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    private lateinit var pokeCard: PokeCard

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = FavCardItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        with(list[position]) {
            holder.binding.apply {
                Glide.with(holder.itemView)
                    .applyDefaultRequestOptions(RequestOptions.fitCenterTransform())
                    .load(list[position].imageUrl)
                    .into(holder.binding.posterImageview)

                holder.binding.nameTextview.text = list[position].name
                holder.binding.setidTextview.text = list[position].pokeID
                holder.binding.cardnumTextview.text = list[position].number.toString()

            holder.binding.posterImageview.setOnClickListener() {
                Log.d("TAG_X", "Clicked on item to delete.")
                pokeCard =PokeCard(
                    list[position].pokeID,
                    list[position].name,
                    list[position].number,
                    list[position].imageUrl
                )
                delegate.selectCard(pokeCard)

            }

            }
        }
    }
    override fun getItemCount(): Int = list.size



}