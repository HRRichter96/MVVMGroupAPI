package com.dynamicdevs.mvvmgroupapi.view.fragment

import com.dynamicdevs.mvvmgroupapi.model.PokeCard

interface PokeSelector {
    fun openDetailsFragment(pokeCard: PokeCard)
    fun openFavsDetailsFragment(pokeCard: PokeCard)
}