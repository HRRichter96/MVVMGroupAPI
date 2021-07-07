package com.dynamicdevs.mvvmgroupapi.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dynamicdevs.mvvmgroupapi.databinding.FragmentFavoritesBinding
import com.dynamicdevs.mvvmgroupapi.model.PokeCard
import com.dynamicdevs.mvvmgroupapi.model.data.Result
import com.dynamicdevs.mvvmgroupapi.view.adapter.FavoriteAdapter
import kotlinx.android.synthetic.main.card_item_view.view.*


class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }
    private val adapter = FavoriteAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favoriteRecyclerview.adapter = adapter

        }


    fun updateFavorites(list: List<PokeCard>){
        adapter.list = list
    }


}