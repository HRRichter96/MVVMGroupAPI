package com.dynamicdevs.mvvmgroupapi.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.request.RequestOptions
import com.dynamicdevs.mvvmgroupapi.databinding.FavoriesFragmentBinding
import com.dynamicdevs.mvvmgroupapi.model.PokeCard
import com.dynamicdevs.mvvmgroupapi.view.activity.MainActivity
import com.dynamicdevs.mvvmgroupapi.view.adapter.FavoriteAdapter
import com.dynamicdevs.mvvmgroupapi.view.adapter.PokeAdapter


class FavoritesFragment : Fragment(), FavoriteAdapter.DeleteDeletage {

    private lateinit var binding: FavoriesFragmentBinding
    private val adapter = FavoriteAdapter(this)
    private lateinit var pokeSelector: PokeSelector

    interface DisplayFavDelegate {
        fun displayFavPokeCard(pokeCard: PokeCard)
    }
    /// binding construct
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        pokeSelector = context as MainActivity
    }


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

    override fun selectCard(pokeCard: PokeCard) {
        Log.d("TAG_X", "Engage PSelector2")
        pokeSelector.openFavsDetailsFragment(pokeCard)
    }


}