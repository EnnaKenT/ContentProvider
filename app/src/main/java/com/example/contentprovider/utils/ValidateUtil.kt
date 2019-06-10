package com.example.contentprovider.utils

import android.widget.EditText

fun EditText.isDbDescriptionValid(): Boolean {
    if (text.isNullOrEmpty()) {
        return false
    }
    return true
}