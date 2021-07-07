package com.dynamicdevs.mvvmgroupapi.model.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.dynamicdevs.mvvmgroupapi.model.PokeCard


@Dao
interface PokeDAO {

    @Insert
    fun insertNewPoke(vararg pokeCard: PokeCard)
    //cannot have both retrofit and roomdatabase to coexist
    @androidx.room.Query("SELECT * FROM pokes")
    fun getAllPokes(): List<PokeCard>

    @Delete
    fun deletePoke(pokeCard: PokeCard)


}