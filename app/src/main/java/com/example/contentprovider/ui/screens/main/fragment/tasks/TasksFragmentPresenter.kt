package com.example.contentprovider.ui.screens.main.fragment.tasks

import com.example.contentprovider.room.AppDatabase
import com.example.contentprovider.room.tasksTable.TaskRoomModel
import com.example.contentprovider.ui.screens.base.BasePresenter
import com.example.contentprovider.ui.screens.main.fragment.TableFragmentContract
import kotlinx.coroutines.launch

class TasksFragmentPresenter : BasePresenter<TableFragmentContract.View<TaskRoomModel>>(),
    TableFragmentContract.Presenter<TaskRoomModel> {

    override fun prepareDatabaseModels() {
        launch {
            view?.showDatabaseModels(AppDatabase.initAppDataBase()?.taskRoomDao()?.getAllTasks())
        }
    }

    override fun onResume() {
        super.onResume()
        prepareDatabaseModels()
    }
}

