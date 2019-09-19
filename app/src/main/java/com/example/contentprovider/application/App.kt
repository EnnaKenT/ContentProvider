package com.example.contentprovider.application

import android.app.Application
import com.example.contentprovider.room.AppDatabase
import com.example.contentprovider.shredPrefs.SharedPreferencesHelper
import kotlinx.coroutines.channels.BroadcastChannel

class App : Application(), AppBridge {

    override val sharedPreferencesHelper: SharedPreferencesHelper by lazy { SharedPreferencesHelper(this) }
    override val broadcastChannel: BroadcastChannel<ChannelEvents> by lazy { BroadcastChannel<ChannelEvents>(1) }

    override fun onCreate() {
        super.onCreate()

        initEntities()
    }

    private fun initEntities() {
        //init db singleton
        AppDatabase.initAppDataBase(this)
    }

}