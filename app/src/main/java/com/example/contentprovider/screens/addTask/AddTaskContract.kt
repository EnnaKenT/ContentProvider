package com.example.contentprovider.screens.addTask

import android.text.Editable
import android.widget.EditText
import com.example.contentprovider.room.tasksTable.TaskRoomModel
import com.example.contentprovider.screens.base.BaseContract

class AddTaskContract {

    interface Presenter : BaseContract.BasePresenter<View> {
        fun saveModel(title: Editable?, description: EditText?)
        fun setModel(taskModel: TaskRoomModel?)
    }

    interface View : BaseContract.BaseView {
    }

}