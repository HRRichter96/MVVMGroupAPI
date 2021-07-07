package com.dynamicdevs.mvvmgroupapi.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dynamicdevs.mvvmgroupapi.model.PokeCard

@Database(version = 1, entities = [PokeCard::class])
abstract class PokeDatabase : RoomDatabase() {
    abstract fun getPokeDao(): PokeDAO
}