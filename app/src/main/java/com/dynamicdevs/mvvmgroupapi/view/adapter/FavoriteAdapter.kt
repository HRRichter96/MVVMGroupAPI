package com.dynamicdevs.mvvmgroupapi.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dynamicdevs.mvvmgroupapi.databinding.FragmentFavoritesBinding
import com.dynamicdevs.mvvmgroupapi.model.PokeCard
import kotlinx.android.synthetic.main.card_item_view.view.*

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    inner class FavoriteViewHolder(val binding: FragmentFavoritesBinding): RecyclerView.ViewHolder(binding.root)
    var list: List<PokeCard> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = FragmentFavoritesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favs = list[position]
        holder.binding.apply {
            holder.binding.favoriteRecyclerview.name_textview.text = favs.name
            holder.binding.favoriteRecyclerview.setid_textview.text = favs.pokeID
            holder.binding.favoriteRecyclerview.cardnum_textview.text = favs.number.toString()
            Glide.with(this.root)
                .applyDefaultRequestOptions(RequestOptions().centerCrop())
                .load("https://images.pokemontcg.io/" +
                        favs.pokeID + "/" +
                        favs.number + ".png")
                .into(holder.binding.favoriteRecyclerview.poster_imageview)

        }
    }

    override fun getItemCount(): Int = list.size



}