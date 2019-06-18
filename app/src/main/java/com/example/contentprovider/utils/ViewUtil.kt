package com.example.contentprovider.utils

import android.app.Activity
import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
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

fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.showKeyboard(view: View) {
    val keyboard = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    keyboard.showSoftInput(view, 0)
}