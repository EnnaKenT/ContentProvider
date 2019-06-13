package com.example.contentprovider.utils

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes

fun View.setGone() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}

fun View.setVisible() {
    isEnabled = true
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

fun View.setInvisible() {
    isEnabled = false
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
}

fun MenuItem.setGone() {
    if (isVisible) {
        isVisible = false
    }
}

fun MenuItem.setVisible() {
    isEnabled = true
    if (!isVisible) {
        isVisible = true
    }
}

/**
 * default setBackgroundTint doesnt work
 */
fun View.setBackgroundTint(color: Int) {
    background?.current?.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY)
}

fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.toast(@StringRes stringRes: Int) =
        Toast.makeText(this, stringRes, Toast.LENGTH_SHORT).show()