package com.example.contentprovider.application

import android.app.Application
import com.example.contentprovider.room.AppDatabase
import com.example.contentprovider.shredPrefs.SharedPreferencesHelper

class App : Application(), AppBridge {

    private var sharedPreferencesHelper: SharedPreferencesHelper? = null
//    private var database: AppDatabase? = null

    override fun onCreate() {
        super.onCreate()

        initEntities()
    }

    private fun initEntities() {
        sharedPreferencesHelper = SharedPreferencesHelper(this)
//        database =
        AppDatabase.initAppDataBase(this)
    }

    override val getSharedPreferencesHelper = sharedPreferencesHelper

//    override val getDatabase = database

}