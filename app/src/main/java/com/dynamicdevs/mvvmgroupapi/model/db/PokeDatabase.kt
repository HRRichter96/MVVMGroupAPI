package com.dynamicdevs.mvvmgroupapi.model.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Result::class])
abstract class PokeDatabase : RoomDatabase() {
    abstract fun getPokeDao(): PokeDAO
}