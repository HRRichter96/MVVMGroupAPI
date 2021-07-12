package com.dynamicdevs.mvvmgroupapi.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dynamicdevs.mvvmgroupapi.databinding.ImageAddFragmentLayoutBinding
import com.dynamicdevs.mvvmgroupapi.databinding.ImageFragmentLayoutBinding
import com.dynamicdevs.mvvmgroupapi.model.PokeCard
import com.dynamicdevs.mvvmgroupapi.view.activity.MainActivity


//after clicking a card item in recyclerview to delete
class ImageFragment: Fragment(){
    private lateinit var binding: ImageFragmentLayoutBinding
    private lateinit var deleteFavoriteDelgate: DeleteFavoriteDelgate

    interface DeleteFavoriteDelgate{
        fun deleteFavorite(pokeCard: PokeCard)
    }

    companion object{
        lateinit var pokeCard: PokeCard
        lateinit var imageFragment: ImageFragment
        const val RESULT_KEY = "RESULT"

        fun getInstance(pokeCard: PokeCard):ImageFragment{
            if(!this::imageFragment.isInitialized)
                imageFragment = ImageFragment()

            return imageFragment.also {
                it.arguments = Bundle().also{
                    it.putParcelable(RESULT_KEY, pokeCard)
                }
            }
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        deleteFavoriteDelgate = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ImageFragmentLayoutBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments.let {
            Glide.with(view)
                .applyDefaultRequestOptions(RequestOptions().fitCenter())
                .load(it?.getString("POKE_URL"))
                .into(binding.imageView)
        }
        //???
        binding.deleteButton.setOnClickListener {
            Log.d("TAG_X", "testing...")
            arguments.let {
                val pokeCard: PokeCard? = it?.getParcelable("POKE_CARD")
                deleteFavoriteDelgate.deleteFavorite(pokeCard!!)
            }
        }

    }
}