package com.example.contentprovider.activity.ui.screens.base

import androidx.annotation.StringRes
import kotlinx.coroutines.CoroutineScope

class BaseContract {

    interface Presenter<V : View>: CoroutineScope {
        var view: V?

        fun onDestroy()
    }

    interface View {

        fun showError(text: String)

        fun showError(@StringRes textRes: Int)
    }

}