package com.example.contentprovider.ui.screens.base

import androidx.annotation.StringRes
import kotlinx.coroutines.CoroutineScope

class BaseContract {

    interface BasePresenter<V : BaseView> : CoroutineScope {
        var view: V?

        fun onStart()

        fun onResume()

        fun onPause()

        fun onStop()

        fun onDestroy()
    }

    interface BaseView {

        fun showError(text: String)

        fun showError(@StringRes stringRes: Int)
    }

}