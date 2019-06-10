package com.example.contentprovider.screens.addTask

import android.app.Activity
import android.content.Intent
import android.view.View
import com.example.contentprovider.R
import com.example.contentprovider.room.tasksTable.TaskRoomModel
import com.example.contentprovider.screens.addNote.AddNoteActivity
import com.example.contentprovider.screens.base.activity.BaseActivity
import com.example.contentprovider.utils.isDbDescriptionValid
import kotlinx.android.synthetic.main.activity_add_note.*

class AddTaskActivity : BaseActivity<AddTaskContract.Presenter, AddTaskContract.View>(),
    AddTaskContract.View, View.OnClickListener {
    private var taskModel: TaskRoomModel? = null

    override val view = this

    override fun createPresenter() = AddTaskPresenter()
    override fun getLayoutId() = R.layout.activity_add_task
    override fun initData() {
        getPresenterRoomModel()
        prepareActionBar()
        mbtn_save_add_item.setOnClickListener(this)
    }

    private fun getPresenterRoomModel() {
        taskModel = intent.getParcelableExtra(ARG_TASK_MODEL)
        presenter.setModel(taskModel)
    }

    private fun prepareActionBar() {
        setSupportActionBar(toolbar)

        supportActionBar?.run {
            title = getString(R.string.task)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mbtn_save_add_item -> onSaveBtnClicked()
        }
    }

    private fun onSaveBtnClicked() {
        if (et_description_add_item.isDbDescriptionValid()) {
            val title = et_title_add_item.text
            val description = et_description_add_item
            presenter.saveModel(title, description)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (taskModel == null) {
            overridePendingTransition(R.anim.slide_from_top, R.anim.slide_to_bottom)
        } else {
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
        }
    }


    companion object {

        private const val ARG_TASK_MODEL = "arg_task_model"


        fun startActivity(activity: Activity, taskModel: TaskRoomModel? = null) {
            val intent = Intent(activity, AddNoteActivity::class.java)
            taskModel?.let { intent.putExtra(ARG_TASK_MODEL, taskModel) }

            activity.startActivity(intent)
        }
    }

}