package com.example.contentprovider.activity.ui.screens.base

import java.lang.ref.WeakReference

abstract class BasePresenterImpl<V : BaseContract.View> : BaseContract.Presenter<V> {
    private var mView: WeakReference<V>? = null

    override fun bindView(view: V) {
        this.mView = WeakReference(view)
    }

    override fun unbindView() {
        this.mView = null
    }

    override fun onDestroy() {
        this.mView = null
    }
}