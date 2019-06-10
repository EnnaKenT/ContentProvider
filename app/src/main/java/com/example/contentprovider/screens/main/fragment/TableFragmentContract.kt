package com.example.contentprovider.screens.main.fragment

import com.example.contentprovider.screens.base.BaseContract

class TableFragmentContract {

    interface Presenter<RoomModel> : BaseContract.BasePresenter<View<RoomModel>> {

        fun prepareDatabaseModels()

    }

    interface View<RoomModel> : BaseContract.BaseView {

        fun showDatabaseModels(models: MutableList<RoomModel>?)
    }

}