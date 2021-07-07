package com.dynamicdevs.mvvmgroupapi.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room

import com.dynamicdevs.mvvmgroupapi.R
import com.dynamicdevs.mvvmgroupapi.databinding.ActivityMainBinding
import com.dynamicdevs.mvvmgroupapi.databinding.FragmentFavoritesBinding
import com.dynamicdevs.mvvmgroupapi.databinding.ImageAddFragmentLayoutBinding
import com.dynamicdevs.mvvmgroupapi.model.PokeCard
import com.dynamicdevs.mvvmgroupapi.model.db.PokeDatabase
import com.dynamicdevs.mvvmgroupapi.view.adapter.PokeAdapter
import com.dynamicdevs.mvvmgroupapi.view.fragment.FavoritesFragment
import com.dynamicdevs.mvvmgroupapi.view.fragment.CardDisplayFragment
import com.dynamicdevs.mvvmgroupapi.view.fragment.ImageAddFragment

class MainActivity : AppCompatActivity(), ImageAddFragment.InsertFragmentCard {
    //database
    private lateinit var pokeDatabase: PokeDatabase
    //displayfragment
    private lateinit var cardDisplayFragment: CardDisplayFragment
    //Favorites Fragment
    private lateinit var imageAddFragment: ImageAddFragment
    private lateinit var pokeCard: PokeCard



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardDisplayFragment =supportFragmentManager.findFragmentById(R.id.display_fragment) as CardDisplayFragment

        pokeDatabase = Room.databaseBuilder(
            this,
            PokeDatabase::class.java,
            "pokecard.db"
        ).allowMainThreadQueries().build()
    }

    override fun selectPokeCard(pokeCard: PokeCard) {
        val bundle = Bundle()
        bundle.putString("POKE_ID", pokeCard.pokeID)
        bundle.putString("POKE_NAME", pokeCard.name)
        bundle.putIntegerArrayList("POKE_NUMBER", pokeCard.number)
        bundle.putString("POKE_URL", pokeCard.imageUrl)
        imageAddFragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, imageAddFragment)
            .addToBackStack(imageAddFragment.tag)
            .commit()
    }

}