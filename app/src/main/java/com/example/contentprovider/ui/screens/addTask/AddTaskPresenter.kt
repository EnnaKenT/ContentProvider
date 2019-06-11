package com.example.contentprovider.ui.screens.addTask

import android.text.Editable
import com.example.contentprovider.room.AppDatabase
import com.example.contentprovider.room.converters.TaskStatusEnum
import com.example.contentprovider.room.tasksTable.TaskRoomModel
import com.example.contentprovider.ui.screens.base.BasePresenter
import kotlinx.coroutines.launch
import java.util.*

class AddTaskPresenter : BasePresenter<AddTaskContract.View>(), AddTaskContract.Presenter {

    private var taskModel: TaskRoomModel? = null

    override fun setRoomModel(taskModel: TaskRoomModel?) {
        this.taskModel = taskModel

        checkExistModel()
    }

    private fun checkExistModel() {
        taskModel?.taskTitle?.let { view?.setTitle(it) }
        taskModel?.taskDescription?.let {
            view?.setDescription(it)
        }
        taskModel?.taskStatusEnum?.let { view?.setStatusEnum(it) }
    }

    override fun checkDeleteIcon() {
        taskModel?.taskDescription?.let {
            view?.enableDeleteBtn()
        }
    }

    override fun deleteItemFromDb() {
        taskModel?.let {
            view?.showProgressBar()

            launch {
                AppDatabase.initAppDataBase()?.taskRoomDao()?.deleteTask(it)

                view?.hideProgressBar()
                view?.taskSaved()
            }
        }
    }

    override fun getRoomModel() = taskModel

    override fun saveModelInDb(title: Editable?, description: Editable, sTaskStatusEnum: String) {
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

