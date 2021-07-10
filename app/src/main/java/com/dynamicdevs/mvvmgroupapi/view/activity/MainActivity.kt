package com.dynamicdevs.mvvmgroupapi.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.dynamicdevs.mvvmgroupapi.R
import com.dynamicdevs.mvvmgroupapi.databinding.ActivityMainBinding
import com.dynamicdevs.mvvmgroupapi.databinding.CardDisplayFragmentBinding
import com.dynamicdevs.mvvmgroupapi.model.PokeCard
import com.dynamicdevs.mvvmgroupapi.model.db.PokeDatabase
import com.dynamicdevs.mvvmgroupapi.view.fragment.CardDisplayFragment
import com.dynamicdevs.mvvmgroupapi.view.fragment.ImageAddFragment
import com.dynamicdevs.mvvmgroupapi.view.fragment.PokeSelector
import com.dynamicdevs.mvvmgroupapi.view.fragment.SearchPokeFragment
import com.dynamicdevs.mvvmgroupapi.viewmodel.CardViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_display_fragment.*
import kotlinx.android.synthetic.main.card_item_view.*
import kotlinx.android.synthetic.main.card_item_view.view.*
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.image_add_fragment_layout.*
import kotlinx.android.synthetic.main.image_add_fragment_layout.view.*
import kotlinx.android.synthetic.main.new_card_fragment_layout.*

class MainActivity : AppCompatActivity(), ImageAddFragment.InsertFragmentCard, CardDisplayFragment.DisplayDelegate, PokeSelector {
    //database
    private lateinit var pokeDatabase: PokeDatabase
    //displayfragment
    private lateinit var cardDisplayFragment: CardDisplayFragment
    private lateinit var searchPokeFragment: SearchPokeFragment
    private val viewModel: CardViewModel by viewModels()
    //Favorites Fragment
    private lateinit var imageAddFragment: ImageAddFragment
    private lateinit var pokeCard: PokeCard


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardDisplayFragment =supportFragmentManager.findFragmentById(R.id.display_fragment) as CardDisplayFragment
        searchPokeFragment =supportFragmentManager.findFragmentById(R.id.entry_fragment) as SearchPokeFragment

        pokeDatabase = Room.databaseBuilder(
            this,
            PokeDatabase::class.java,
            "Pokes.db"
        ).allowMainThreadQueries().build()

        searchPokeFragment.search_button.setOnClickListener{
            Log.d("TAG_X", "Click on search button...")

            val pokeName = searchPokeFragment.name_editText.text.toString().trim()
            val pokeID = searchPokeFragment.num_editText.text.toString().trim()

            //viewModel
            if (pokeID == ""){
                viewModel.searchCards("name:$pokeName")
                Log.d("TAG_X", "Sent Name...")

            }else if(pokeName ==""){
                viewModel.searchCards("nationalPokedexNumbers:$pokeID")
            }
            searchPokeFragment.name_editText.text.clear()
            searchPokeFragment.num_editText.text.clear()
            supportFragmentManager.beginTransaction()
                .commit()
        }

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


    override fun openDetailsFragment(pokeCard: PokeCard) {
        val fragment = ImageAddFragment.getInstance(pokeCard)
        Log.d("TAG_X", "odf")
        Log.d("TAG_X", "${pokeCard.imageUrl} is the url of the image.")

        fragment.arguments.let {
            it?.putString("POKE_URL", pokeCard.imageUrl)
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.main_frame,fragment)
            .addToBackStack(fragment.tag)
            .commit()


    }

    override fun displayPokeCard(pokeCard: PokeCard) {
    }


}