package com.example.contentprovider.activity.ui.screens.main

class MainPresenterImpl : MainPresenter {
    private var mainView: MainView? = null

    override fun bindView(view: MainView) {
        this.mainView = view
    }

    override fun unbindView() {
        this.mainView = null
    }

    override fun onDestroy() {
        this.mainView = null
    }
}