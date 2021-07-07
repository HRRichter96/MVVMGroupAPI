package com.dynamicdevs.mvvmgroupapi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.reflect.Constructor


@Entity(tableName = "Pokes")
data class PokeCard(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "poke_Id")
    val pokeID: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "number")
    val number: Int,
    @ColumnInfo(name = "image_Url")
    val imageUrl: String
) {
//    constructor(pokeID: String,name: String,number: Int,imageUrl: String): this (pokeID,name,number,imageUrl)
}
