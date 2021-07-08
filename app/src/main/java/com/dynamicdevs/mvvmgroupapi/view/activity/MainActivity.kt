package com.dynamicdevs.mvvmgroupapi.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room

import com.dynamicdevs.mvvmgroupapi.R
import com.dynamicdevs.mvvmgroupapi.databinding.ActivityMainBinding
import com.dynamicdevs.mvvmgroupapi.databinding.CardDisplayFragmentBinding
import com.dynamicdevs.mvvmgroupapi.model.PokeCard
import com.dynamicdevs.mvvmgroupapi.model.db.PokeDatabase
import com.dynamicdevs.mvvmgroupapi.view.fragment.CardDisplayFragment
import com.dynamicdevs.mvvmgroupapi.view.fragment.ImageAddFragment
import kotlinx.android.synthetic.main.card_display_fragment.*
import kotlinx.android.synthetic.main.card_item_view.*

class MainActivity : AppCompatActivity(), ImageAddFragment.InsertFragmentCard, CardDisplayFragment.DisplayDelegate {
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
            "Pokes.db"
        ).allowMainThreadQueries().build()
    }

    override fun selectPokeCard(pokeCard: PokeCard) {
        val bundle = Bundle()
        bundle.putString("POKE_ID", pokeCard.pokeID)
        bundle.putString("POKE_NAME", pokeCard.name)
        bundle.putInt("POKE_NUMBER", pokeCard.number)
        bundle.putString("POKE_URL", pokeCard.imageUrl)
        imageAddFragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, imageAddFragment)
            .addToBackStack(imageAddFragment.tag)
            .commit()
    }

    override fun displayPokeCard(pokeCard: PokeCard) {

    }


}