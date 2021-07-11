package com.dynamicdevs.mvvmgroupapi.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dynamicdevs.mvvmgroupapi.databinding.FavoriesFragmentBinding
import com.dynamicdevs.mvvmgroupapi.model.PokeCard
import com.dynamicdevs.mvvmgroupapi.view.adapter.FavoriteAdapter


class FavoritesFragment : Fragment() {

    private lateinit var binding: FavoriesFragmentBinding

    interface DisplayFavDelegate {
        fun displayFavPokeCard(pokeCard: PokeCard)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavoriesFragmentBinding.inflate(
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

    companion object{
        lateinit var favoritesFragment: FavoritesFragment

        fun getInstance(): FavoritesFragment {
            if(!this::favoritesFragment.isInitialized)
                favoritesFragment = FavoritesFragment()

            return favoritesFragment

        }
    }

    fun updateFavorites(list: List<PokeCard>){
        adapter.list = list
    }


}