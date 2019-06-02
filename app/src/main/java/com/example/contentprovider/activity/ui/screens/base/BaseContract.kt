package com.example.contentprovider.activity.ui.screens.base

class BaseContract {

    interface Presenter<in V : View> {

        fun bindView(view: V)

        fun unbindView()

        fun onDestroy()
    }

    interface View {
    }

}