package com.dynamicdevs.mvvmgroupapi

import android.app.Application
import com.dynamicdevs.mvvmgroupapi.model.db.PokeDatabase

class PokeApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        PokeDatabase.initializeDatabase(this)
    }

}