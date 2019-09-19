package com.example.contentprovider.application

import com.example.contentprovider.shredPrefs.SharedPreferencesHelper
import kotlinx.coroutines.channels.BroadcastChannel

interface AppBridge {
    val sharedPreferencesHelper: SharedPreferencesHelper
    val broadcastChannel: BroadcastChannel<ChannelEvents>
}