package com.dynamicdevs.mvvmgroupapi.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dynamicdevs.mvvmgroupapi.databinding.NewCardFragmentLayoutBinding
import com.dynamicdevs.mvvmgroupapi.model.PokeCard
import com.dynamicdevs.mvvmgroupapi.view.activity.MainActivity
import com.dynamicdevs.mvvmgroupapi.view.adapter.PokeAdapter

import com.dynamicdevs.mvvmgroupapi.viewmodel.CardViewModel


//first half of MainActivity, search pokecard
class SearchPokeFragment: Fragment() {
    private lateinit var binding: NewCardFragmentLayoutBinding
    private val viewModel: CardViewModel by viewModels()
    private val adapter= PokeAdapter()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewCardFragmentLayoutBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }
    //input card
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchButton.setOnClickListener {
            val pokename=binding.nameEditText.text.toString().trim()
            binding.nameEditText.text.clear()
            val pokeid=binding.numEditText.text.toString().toInt()
            binding.numEditText.text.clear()
            //viewmodel
            if (pokeid == null){
                viewModel.searchCards("name:".trim()+pokename)
            }else if(pokename ==""){
                viewModel.searchCards("nationalPokedexNumbers:[".trim()+pokeid+"]")
            }
        }
        //?since this is not MainActivity
        viewModel.cardLiveData.observe(viewLifecycleOwner,{
            adapter.pokes=it
        } )
    }
}