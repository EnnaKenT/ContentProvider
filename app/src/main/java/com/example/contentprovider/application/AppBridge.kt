package com.example.contentprovider.application

import com.example.contentprovider.shredPrefs.SharedPreferencesHelper

interface AppBridge {
    val getSharedPreferencesHelper: SharedPreferencesHelper?
//    val getDatabase: AppDatabase?
}