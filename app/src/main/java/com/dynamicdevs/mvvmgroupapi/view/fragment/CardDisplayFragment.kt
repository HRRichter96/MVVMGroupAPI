package com.dynamicdevs.mvvmgroupapi.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dynamicdevs.mvvmgroupapi.databinding.CardDisplayFragmentBinding
import com.dynamicdevs.mvvmgroupapi.view.adapter.PokeAdapter


//Second Half of MainActivity
class CardDisplayFragment: Fragment() {

    private lateinit var binding: CardDisplayFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CardDisplayFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    private val adapter = PokeAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cardRecyclerview.adapter = adapter
    }

}