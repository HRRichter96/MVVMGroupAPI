package com.dynamicdevs.mvvmgroupapi.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dynamicdevs.mvvmgroupapi.databinding.CardDisplayFragmentBinding
import com.dynamicdevs.mvvmgroupapi.model.PokeCard
import com.dynamicdevs.mvvmgroupapi.model.data.Result
import com.dynamicdevs.mvvmgroupapi.view.activity.MainActivity
import com.dynamicdevs.mvvmgroupapi.view.adapter.PokeAdapter
import com.dynamicdevs.mvvmgroupapi.viewmodel.CardViewModel


//Second Half of MainActivity
class CardDisplayFragment: Fragment(), PokeAdapter.PokeDelegate {

    private val adapter = PokeAdapter(this)
    private lateinit var binding: CardDisplayFragmentBinding
//    private lateinit var delegate: DisplayDelegate
    private val viewModel by activityViewModels<CardViewModel>()
    private lateinit var pokeSelector: PokeSelector

    interface DisplayDelegate {
        fun displayPokeCard(pokeCard: PokeCard)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        delegate = context as MainActivity
        pokeSelector = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CardDisplayFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cardRecyclerview.adapter = adapter
        Log.d("TAG_X", "Observing in CardDisplayFragment...")
        viewModel.cardLiveData.observe(viewLifecycleOwner, {
            adapter.pokes = it
        })
        Log.d("TAG_X", "Got to the onViewCreated - CardDisplayFragment")
        adapter.notifyDataSetChanged()

    }

    //pre to imageaddfragment
    override fun selectCard(pokeCard: PokeCard) {
        Log.d("TAG_X", "Engage PSelector")
        pokeSelector.openDetailsFragment(pokeCard)

    }

}