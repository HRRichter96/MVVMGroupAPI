package com.dynamicdevs.mvvmgroupapi.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dynamicdevs.mvvmgroupapi.databinding.ImageAddFragmentLayoutBinding
import com.dynamicdevs.mvvmgroupapi.model.PokeCard
import com.dynamicdevs.mvvmgroupapi.model.db.PokeDatabase.Companion.getPokeDao
import com.dynamicdevs.mvvmgroupapi.view.activity.MainActivity

class ImageAddFragment: Fragment() {

    private lateinit var binding: ImageAddFragmentLayoutBinding
    private lateinit var insertFavoriteDelegate: InsertFavoriteDelegate

    interface InsertFavoriteDelegate {
        fun insertFavorite(pokeCard: PokeCard)
    }

    companion object{
        lateinit var imageAddFragment: ImageAddFragment
        const val RESULT_KEY = "RESULT"
        lateinit var pokeCard: PokeCard

        fun getInstance(pokeCard: PokeCard): ImageAddFragment {
            if(!this::imageAddFragment.isInitialized)
                imageAddFragment = ImageAddFragment()

            return imageAddFragment.also {
                it.arguments = Bundle().also {
                    it.putParcelable(RESULT_KEY, pokeCard)
                }
            }
        }
    }

    interface InsertFragmentCard {
        fun selectPokeCard(pokeCard: PokeCard)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ImageAddFragmentLayoutBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        insertFavoriteDelegate = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments.let {
            Glide.with(view)
                .applyDefaultRequestOptions(RequestOptions().fitCenter())
                .load(it?.getString("POKE_URL"))
                .into(binding.largeImageView)
        }
        //???
        binding.addButton.setOnClickListener {
            arguments.let {
                val pokeCard: PokeCard? = it?.getParcelable("POKE_CARD")

                insertFavoriteDelegate.insertFavorite(pokeCard!!)


            }
            Log.d("TAG_X", "Add Test")
        }

    }

}