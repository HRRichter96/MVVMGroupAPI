package com.dynamicdevs.mvvmgroupapi.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.lang.reflect.Constructor

@Parcelize
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
): Parcelable {
//    constructor(pokeID: String,name: String,number: Int,imageUrl: String): this (pokeID,name,number,imageUrl)
}
