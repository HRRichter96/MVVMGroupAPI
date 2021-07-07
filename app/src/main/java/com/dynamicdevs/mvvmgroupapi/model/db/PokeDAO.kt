package com.dynamicdevs.mvvmgroupapi.model.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.dynamicdevs.mvvmgroupapi.model.PokeCard


@Dao
interface PokeDAO {

    @Insert
    fun insertNewPoke(vararg pokeCard: PokeCard)

    @androidx.room.Query("SELECT * FROM Pokes")
    fun getAllPokes(): List<PokeCard>

    @Delete
    fun deletePoke(pokeCard: PokeCard)


}