package com.example.contentprovider.screens.main.fragment

import com.example.contentprovider.screens.base.BaseContract

class TableFragmentContract {

    interface Presenter<RoomModel> : BaseContract.Presenter<View<RoomModel>> {

        fun prepareDatabaseModels()

    }

    interface View<RoomModel> : BaseContract.View {

        fun showDatabaseModels(models: MutableList<RoomModel>?)
    }

}