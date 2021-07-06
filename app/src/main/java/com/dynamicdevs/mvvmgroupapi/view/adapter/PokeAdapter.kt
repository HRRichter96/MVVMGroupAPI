package com.dynamicdevs.mvvmgroupapi.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import androidx.recyclerview.widget.RecyclerView
import com.dynamicdevs.mvvmgroupapi.databinding.CardItemViewBinding
import com.dynamicdevs.mvvmgroupapi.model.data.Result

class PokeAdapter(private val delegate: PokeDelegate):RecyclerView.Adapter<PokeAdapter.PokeViewHolder>() {

    interface PokeDelegate {
        fun selectImage(url: String)
    }

    inner class PokeViewHolder(val binding: CardItemViewBinding): RecyclerView.ViewHolder(binding.root)

    var pokes: List<Result> = listOf()
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
        with(pokes[position]){
            holder.binding.apply {
                Glide.with(holder.itemView)
                    .applyDefaultRequestOptions(RequestOptions.circleCropTransform())
                    .load(images.large)
                    .into(posterImageview)

                posterImageview.setOnClickListener {
                    delegate.selectImage(images.large)
                }
                nameTextview.text = name
                setidTextview.text = set.id
                cardnumTextview.text = nationalPokedexNumbers.toString()


            }
        }
    }

    override fun getItemCount() = pokes.size


}