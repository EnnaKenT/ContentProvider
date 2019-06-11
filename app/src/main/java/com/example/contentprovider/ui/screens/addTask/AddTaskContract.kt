package com.example.contentprovider.ui.screens.addTask

import android.text.Editable
import com.example.contentprovider.room.converters.TaskStatusEnum
import com.example.contentprovider.room.tasksTable.TaskRoomModel
import com.example.contentprovider.ui.screens.base.BaseContract

class AddTaskContract {

    interface Presenter : BaseContract.BasePresenter<View> {
        fun setRoomModel(taskModel: TaskRoomModel?)
        fun getRoomModel(): TaskRoomModel?
        fun saveModelInDb(title: Editable?, description: Editable, sTaskStatusEnum: String)
        fun checkDeleteIcon()
        fun deleteItemFromDb()
    }

    interface View : BaseContract.BaseView {
        fun taskSaved()
        fun showProgressBar()
        fun hideProgressBar()
        fun setTitle(title: String)
        fun setDescription(description: String)
        fun setStatusEnum(taskStatusEnum: TaskStatusEnum)
        fun enableDeleteBtn()
    }

}