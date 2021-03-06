package com.dynamicdevs.mvvmgroupapi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dynamicdevs.mvvmgroupapi.model.data.PokeResponse
import com.dynamicdevs.mvvmgroupapi.model.data.Result
import com.dynamicdevs.mvvmgroupapi.model.network.PokeRetrofit
import com.dynamicdevs.mvvmgroupapi.view.adapter.PokeAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardViewModel: ViewModel() {



    val cardLiveData=MutableLiveData<List<Result>>()
    private val pokeRetrofit = PokeRetrofit
    //?

    fun searchCards(name: String){
        pokeRetrofit.getPoke(name).enqueue(object: Callback<PokeResponse>{
            override fun onResponse(call: Call<PokeResponse>, response: Response<PokeResponse>) {
                Log.d("TAG_X","Got to the CardViewModel" )
                response.body()?.let {
                    cardLiveData.postValue(it.data)
                }
            }

            override fun onFailure(call: Call<PokeResponse>, t: Throwable) {
                Log.d("TAG_X", t.message.toString())
            }

        })
    }

}