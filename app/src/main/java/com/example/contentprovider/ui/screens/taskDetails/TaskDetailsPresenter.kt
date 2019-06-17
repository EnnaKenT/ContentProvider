package com.example.contentprovider.ui.screens.taskDetails

import android.content.Intent
import android.text.Editable
import com.example.contentprovider.room.AppDatabase
import com.example.contentprovider.room.converters.TaskStatusEnum
import com.example.contentprovider.room.tasksTable.TaskRoomModel
import com.example.contentprovider.ui.screens.base.BasePresenter
import com.example.contentprovider.utils.getTaskShareIntent
import kotlinx.coroutines.launch
import java.util.*

class TaskDetailsPresenter : BasePresenter<TaskDetailsContract.View>(), TaskDetailsContract.Presenter {

    private var taskModel: TaskRoomModel? = null
    private var taskId: Int? = null

    override fun setRoomModel(taskModel: TaskRoomModel?, taskId: Int?) {
        this.taskModel = taskModel
        this.taskId = taskId

        if (taskModel == null && taskId != null) {
            getTaskById()
        } else {
            checkExistModel()
        }
    }

    private fun getTaskById() {
        launch {
            view?.showProgressBar()
            val taskModel = AppDatabase.getAppDataBase()?.taskRoomDao()?.getTaskById(taskId!!)
            setRoomModel(taskModel, taskId)
            view?.hideProgressBar()
        }
    }

    private fun checkExistModel() {
        taskModel?.title?.let { view?.setTitle(it) }
        taskModel?.description?.let {
            view?.setDescription(it)
            view?.enableBottomBarBtns()
        }
        taskModel?.taskStatusEnum?.let { view?.setStatusEnum(it) }
    }

    override fun getUriIntent(): Intent {
        return Intent().getTaskShareIntent(taskModel)
    }

    override fun deleteItemFromDb() {
        taskModel?.let {
            view?.showProgressBar()

            launch {
                AppDatabase.getAppDataBase()?.taskRoomDao()?.deleteTask(it)

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
                taskModel?.createdTime ?: Date(System.currentTimeMillis()),
                taskStatusEnum
        )

        launch {
            AppDatabase.getAppDataBase()?.taskRoomDao()?.insertTask(newTaskModel)
            view?.hideProgressBar()
            view?.taskSaved()
        }
    }
}

