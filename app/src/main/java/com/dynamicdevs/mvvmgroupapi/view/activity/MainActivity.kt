package com.dynamicdevs.mvvmgroupapi.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
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
import com.dynamicdevs.mvvmgroupapi.view.fragment.*
import com.dynamicdevs.mvvmgroupapi.viewmodel.CardViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_display_fragment.*
import kotlinx.android.synthetic.main.card_item_view.*
import kotlinx.android.synthetic.main.card_item_view.view.*
import kotlinx.android.synthetic.main.favories_fragment.*
import kotlinx.android.synthetic.main.image_add_fragment_layout.*
import kotlinx.android.synthetic.main.image_add_fragment_layout.view.*
import kotlinx.android.synthetic.main.new_card_fragment_layout.*

class MainActivity : AppCompatActivity(), ImageAddFragment.InsertFragmentCard, ImageAddFragment.InsertFavoriteDelegate, CardDisplayFragment.DisplayDelegate, FavoritesFragment.DisplayFavDelegate, PokeSelector {
    //database
    private lateinit var pokeDatabase: PokeDatabase
    //displayfragment
    private lateinit var cardDisplayFragment: CardDisplayFragment
    private lateinit var searchPokeFragment: SearchPokeFragment
    private val viewModel: CardViewModel by viewModels()
    //Favorites Fragment
    private lateinit var imageAddFragment: ImageAddFragment
    private lateinit var favoriteFragment: FavoritesFragment
    private lateinit var pokeCard: PokeCard


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardDisplayFragment =supportFragmentManager.findFragmentById(R.id.display_fragment) as CardDisplayFragment
        searchPokeFragment =supportFragmentManager.findFragmentById(R.id.entry_fragment) as SearchPokeFragment
//        imageAddFragment = supportFragmentManager.findFragmentById(R.id.main_frame) as ImageAddFragment
//        favoriteFragment = supportFragmentManager.findFragmentById(R.id.favorite_frame) as FavoritesFragment

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

        searchPokeFragment.fav_button.setOnClickListener{
            Log.d("TAG_X", "Click on the goto favorites button")
            val fragment = FavoritesFragment.getInstance()

            readFromDB()
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_frame, fragment)
                .addToBackStack(fragment.tag)
                .commit()

        }

    }

    override fun selectPokeCard(pokeCard: PokeCard) {
        val bundle = Bundle()
        bundle.putString("POKE_ID", pokeCard.pokeID)
        bundle.putString("POKE_NAME", pokeCard.name)
        bundle.putInt("POKE_NUMBER", pokeCard.number)
        bundle.putString("POKE_URL", pokeCard.imageUrl)

//        supportFragmentManager.beginTransaction()
//            .replace(R.id.main_frame, imageAddFragment)
//            .addToBackStack(imageAddFragment.tag)
//            .commit()
    }


    override fun openDetailsFragment(pokeCard: PokeCard) {
        val fragment = ImageAddFragment.getInstance(pokeCard)
        Log.d("TAG_X", "odf")
        Log.d("TAG_X", "${pokeCard.imageUrl} is the url of the image.")

        fragment.arguments.let {
            it?.putString("POKE_URL", pokeCard.imageUrl)
            it?.putParcelable("POKE_CARD", pokeCard)
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.main_frame,fragment)
            .addToBackStack(fragment.tag)
            .commit()


    }

    override fun displayPokeCard(pokeCard: PokeCard) {
    }

    override fun displayFavPokeCard(pokeCard: PokeCard) {
        TODO("Not yet implemented")
    }

    override fun insertFavorite(pokeCard: PokeCard) {
        if(pokeDatabase.getPokeDao().getAllPokes().contains(pokeCard)){
            Toast.makeText(this,"This Pokemon card is already added to favorites.", Toast.LENGTH_LONG).show()
        } else{
            pokeDatabase.getPokeDao().insertNewPoke(pokeCard)
        }
        readFromDB()

    }

    private fun readFromDB() {
        val fragment = FavoritesFragment.getInstance()
        fragment.updateFavorites(pokeDatabase.getPokeDao().getAllPokes())
    }


}