package com.example.contentprovider.screens.base

import androidx.annotation.StringRes
import kotlinx.coroutines.CoroutineScope

class BaseContract {

    interface Presenter<V : View> : CoroutineScope {
        var view: V?

        fun onStart()

        fun onResume()

        fun onPause()

        fun onStop()

        fun onDestroy()
    }

    interface View {

        fun showError(text: String)

        fun showError(@StringRes stringRes: Int)
    }

}