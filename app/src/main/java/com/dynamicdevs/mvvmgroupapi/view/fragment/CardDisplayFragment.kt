package com.dynamicdevs.mvvmgroupapi.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dynamicdevs.mvvmgroupapi.databinding.CardDisplayFragmentBinding
import com.dynamicdevs.mvvmgroupapi.model.PokeCard
import com.dynamicdevs.mvvmgroupapi.model.data.Result
import com.dynamicdevs.mvvmgroupapi.view.adapter.PokeAdapter


//Second Half of MainActivity
class CardDisplayFragment: Fragment() {

    private lateinit var displayDelegate: DisplayDelegate
    private val adapter = PokeAdapter.instance
    private lateinit var binding: CardDisplayFragmentBinding
    private lateinit var delegate: PokeAdapter.PokeDelegate

    interface DisplayDelegate {
        fun updateList(pokes: List<Result>)
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
        adapter.notifyDataSetChanged()


        Log.d("TAG_X", "Got to the onViewCreated - CardDisplayFragment")
    }

    fun updateCards(list: List<Result>){
        PokeAdapter.instance.pokes=list
    }

}