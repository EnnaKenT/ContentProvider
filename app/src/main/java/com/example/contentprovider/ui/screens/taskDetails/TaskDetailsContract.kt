package com.example.contentprovider.ui.screens.taskDetails

import android.content.Intent
import android.text.Editable
import com.example.contentprovider.room.converters.TaskStatusEnum
import com.example.contentprovider.room.tasksTable.TaskRoomModel
import com.example.contentprovider.ui.screens.base.BaseContract

class TaskDetailsContract {

    interface Presenter : BaseContract.BasePresenter<View> {
        fun setRoomModel(taskModel: TaskRoomModel?, taskId: Int?)
        fun getRoomModel(): TaskRoomModel?
        fun saveModelInDb(title: Editable?, description: Editable, sTaskStatusEnum: String)
        fun deleteItemFromDb()
        fun getUriIntent(): Intent
    }

    interface View : BaseContract.BaseView {
        fun taskSaved()
        fun showProgressBar()
        fun hideProgressBar()
        fun setTitle(title: String)
        fun setDescription(description: String)
        fun setStatusEnum(taskStatusEnum: TaskStatusEnum)
        fun enableBottomBarBtns()
    }

}