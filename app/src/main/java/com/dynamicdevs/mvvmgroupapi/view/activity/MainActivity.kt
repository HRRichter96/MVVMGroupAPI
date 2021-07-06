package com.dynamicdevs.mvvmgroupapi.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room

import com.dynamicdevs.mvvmgroupapi.R
import com.dynamicdevs.mvvmgroupapi.databinding.ActivityMainBinding
import com.dynamicdevs.mvvmgroupapi.databinding.FragmentFavoritesBinding
import com.dynamicdevs.mvvmgroupapi.model.db.PokeDatabase
import com.dynamicdevs.mvvmgroupapi.view.adapter.PokeAdapter
import com.dynamicdevs.mvvmgroupapi.view.fragment.CardDisplayFragment

class MainActivity : AppCompatActivity() {
    //database
    private lateinit var pokeDatabase: PokeDatabase
    //displayfragment
    private lateinit var cardDisplayFragment: CardDisplayFragment
    //Favorites Fragment
    private lateinit var favoriteFragment: FragmentFavoritesBinding

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
}