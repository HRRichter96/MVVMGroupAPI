package com.dynamicdevs.mvvmgroupapi.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dynamicdevs.mvvmgroupapi.databinding.FavCardItemViewBinding
import com.dynamicdevs.mvvmgroupapi.databinding.FavoriesFragmentBinding
import com.dynamicdevs.mvvmgroupapi.model.PokeCard
import kotlinx.android.synthetic.main.card_item_view.view.*
import kotlinx.android.synthetic.main.card_item_view.view.name_textview
import kotlinx.android.synthetic.main.fav_card_item_view.view.*

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    inner class FavoriteViewHolder(val binding: FavCardItemViewBinding):
        RecyclerView.ViewHolder(binding.root)

    var list: List<PokeCard> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

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
                    .applyDefaultRequestOptions(RequestOptions.circleCropTransform())
                    .load(list[position].imageUrl)
                    .into(holder.binding.posterImageview)

                holder.binding.nameTextview.text = list[position].name
                holder.binding.setidTextview.text = list[position].pokeID
                holder.binding.cardnumTextview.text = list[position].number.toString()


            }
        }
    }
    override fun getItemCount(): Int = list.size



}