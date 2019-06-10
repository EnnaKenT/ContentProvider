package com.example.contentprovider.screens.addTask

import android.text.Editable
import com.example.contentprovider.room.AppDatabase
import com.example.contentprovider.room.converters.TaskStatusEnum
import com.example.contentprovider.room.tasksTable.TaskRoomModel
import com.example.contentprovider.screens.base.BasePresenter
import kotlinx.coroutines.launch
import java.util.*

class AddTaskPresenter : BasePresenter<AddTaskContract.View>(), AddTaskContract.Presenter {

    private var taskModel: TaskRoomModel? = null

    override fun setModel(taskModel: TaskRoomModel?) {
        this.taskModel = taskModel
    }

    override fun saveModel(title: Editable?, description: Editable, sTaskStatusEnum: String) {
        view?.showProgressBar()
        val taskStatusEnum = TaskStatusEnum.values().first { it.text == sTaskStatusEnum }

        val newTaskModel = TaskRoomModel(
                taskModel?.id,
                title.toString(),
                description.toString(),
                taskModel?.taskCreatedTime ?: Date(System.currentTimeMillis()),
                taskStatusEnum
        )

        launch {
            AppDatabase.initAppDataBase()?.taskRoomDao()?.insertTask(newTaskModel)
            view?.hideProgressBar()
            view?.taskSaved()
        }
    }
}

