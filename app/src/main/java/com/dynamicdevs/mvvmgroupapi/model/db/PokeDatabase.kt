package com.dynamicdevs.mvvmgroupapi.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dynamicdevs.mvvmgroupapi.model.PokeCard
import com.dynamicdevs.mvvmgroupapi.util.Constants.Companion.DATABASE_NAME

@Database(version = 1, entities = [PokeCard::class])
abstract class PokeDatabase : RoomDatabase() {

    abstract fun getPokeDao(): PokeDAO

    companion object {
        private lateinit var pokeDatabase: PokeDatabase

        fun initializeDatabase(context: Context) {
            pokeDatabase = Room.databaseBuilder(
                context,
                PokeDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
        fun getPokeDao() = pokeDatabase.getPokeDao()
    }
}