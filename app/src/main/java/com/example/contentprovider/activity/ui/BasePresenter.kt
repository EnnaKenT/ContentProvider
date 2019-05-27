package com.example.contentprovider.activity.ui

interface BasePresenter<in V : BaseView> {

    fun bindView(view: V)

    fun unbindView()

    fun onDestroy()
}