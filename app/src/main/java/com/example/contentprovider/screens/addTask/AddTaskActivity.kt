package com.example.contentprovider.screens.addTask

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.ArrayAdapter
import com.example.contentprovider.R
import com.example.contentprovider.room.converters.TaskStatusEnum
import com.example.contentprovider.room.tasksTable.TaskRoomModel
import com.example.contentprovider.screens.base.activity.BaseActivity
import com.example.contentprovider.utils.isDbDescriptionValid
import com.example.contentprovider.utils.setGone
import com.example.contentprovider.utils.setVisible
import kotlinx.android.synthetic.main.activity_add_task.*

class AddTaskActivity : BaseActivity<AddTaskContract.Presenter, AddTaskContract.View>(),
        AddTaskContract.View, View.OnClickListener {
    private var taskModel: TaskRoomModel? = null

    override val view = this

    override fun createPresenter() = AddTaskPresenter()
    override fun getLayoutId() = R.layout.activity_add_task

    override fun initData() {
        getPresenterRoomModel()
        prepareActionBar()
        prepareSpinner()
        mbtn_save_add_task.setOnClickListener(this)
    }

    private fun prepareSpinner() {
        val data = TaskStatusEnum.values().map { it.text }
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_task.adapter = adapter
    }

    override fun showProgressBar() {
        fl_progress_bar.setVisible()
    }

    override fun hideProgressBar() {
        fl_progress_bar.setGone()
    }

    private fun getPresenterRoomModel() {
        taskModel = intent.getParcelableExtra(ARG_TASK_MODEL)
        presenter.setModel(taskModel)
    }

    private fun prepareActionBar() {
        setSupportActionBar(toolbar_add_task)

        supportActionBar?.run {
            title = getString(com.example.contentprovider.R.string.task)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mbtn_save_add_task -> onSaveBtnClicked()
        }
    }

    private fun onSaveBtnClicked() {
        et_description_add_task.error = ""
        if (et_description_add_task.isDbDescriptionValid()) {
            val title = et_title_add_task.text
            val description = et_description_add_task.text
            presenter.saveModel(title, description, spinner_task.selectedItem.toString())
        } else {
            et_description_add_task.error = getString(R.string.descr_error)
        }
    }

    override fun taskSaved() {
        onBackPressed()
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
            val intent = Intent(activity, AddTaskActivity::class.java)
            taskModel?.let { intent.putExtra(ARG_TASK_MODEL, taskModel) }

            activity.startActivity(intent)
        }
    }

}