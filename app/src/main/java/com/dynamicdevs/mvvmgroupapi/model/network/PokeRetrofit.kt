package com.dynamicdevs.mvvmgroupapi.model.network

import com.dynamicdevs.mvvmgroupapi.model.data.PokeResponse
import com.dynamicdevs.mvvmgroupapi.util.Constants.Companion.BASE_URL
import com.dynamicdevs.mvvmgroupapi.util.Constants.Companion.END_POINT
import com.dynamicdevs.mvvmgroupapi.util.Constants.Companion.SEARCH_QUERY
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object PokeRetrofit {

    private val pokeService = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(PokeService::class.java)

    fun getPoke(search: String): Call<PokeResponse> = pokeService.getResponse(search.replace("%3A", ":"))

    interface PokeService {
        @GET(END_POINT)
        fun getResponse(@Query(SEARCH_QUERY) searchName: String): Call<PokeResponse>
    }

}