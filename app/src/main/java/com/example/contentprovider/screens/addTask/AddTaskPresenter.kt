package com.example.contentprovider.screens.addTask

import android.text.Editable
import android.widget.EditText
import com.example.contentprovider.room.tasksTable.TaskRoomModel
import com.example.contentprovider.screens.base.BasePresenter

class AddTaskPresenter : BasePresenter<AddTaskContract.View>(), AddTaskContract.Presenter {

    private var taskModel: TaskRoomModel? = null

    override fun setModel(taskModel: TaskRoomModel?) {
        this.taskModel = taskModel
    }

    override fun saveModel(title: Editable?, description: EditText?) {

    }
}

