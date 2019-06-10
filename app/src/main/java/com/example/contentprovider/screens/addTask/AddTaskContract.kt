package com.example.contentprovider.screens.addTask

import android.text.Editable
import com.example.contentprovider.room.tasksTable.TaskRoomModel
import com.example.contentprovider.screens.base.BaseContract

class AddTaskContract {

    interface Presenter : BaseContract.BasePresenter<View> {
        fun saveModel(title: Editable?, description: Editable, sTaskStatusEnum: String)
        fun setModel(taskModel: TaskRoomModel?)
    }

    interface View : BaseContract.BaseView {
        fun taskSaved()
        fun showProgressBar()
        fun hideProgressBar()
    }

}