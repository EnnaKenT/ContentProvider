package com.example.contentprovider.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.roomModelTimeToString(): String {
    return SimpleDateFormat("d/MM/yy").format(this)
}