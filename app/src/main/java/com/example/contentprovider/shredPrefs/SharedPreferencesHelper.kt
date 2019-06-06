package com.example.contentprovider.shredPrefs

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context) {

    private val mSharedPreferences: SharedPreferences

    init {
        mSharedPreferences = context.getSharedPreferences(SHARED_PREF_ID, Context.MODE_PRIVATE)
    }

    fun storagePermissionWasAsked() {
        mSharedPreferences.edit().putBoolean(STORAGE_PERMISSION, true).apply()
    }

    fun wasStoragePermissionAsked(): Boolean {
        return mSharedPreferences.getBoolean(STORAGE_PERMISSION, false)
    }

    fun cameraPermissionWasAsked() {
        mSharedPreferences.edit().putBoolean(CAMERA_PERMISSION, true).apply()
    }

    fun wasCameraPermissionAsked(): Boolean {
        return mSharedPreferences.getBoolean(CAMERA_PERMISSION, false)
    }

    companion object {
        private const val SHARED_PREF_ID = "com.rodo"
        private const val STORAGE_PERMISSION = "storage_perm"
        private const val CAMERA_PERMISSION = "camera_perm"
    }

}