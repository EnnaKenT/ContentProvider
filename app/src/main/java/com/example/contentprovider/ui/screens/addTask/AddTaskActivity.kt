package com.example.contentprovider.ui.screens.addTask

import android.app.Activity
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.widget.Toolbar
import com.example.contentprovider.R
import com.example.contentprovider.room.converters.TaskStatusEnum
import com.example.contentprovider.room.tasksTable.TaskRoomModel
import com.example.contentprovider.ui.dialog.OkCancelDialog
import com.example.contentprovider.ui.screens.base.activity.BaseActivity
import com.example.contentprovider.utils.isDbDescriptionValid
import com.example.contentprovider.utils.setGone
import com.example.contentprovider.utils.setVisible
import kotlinx.android.synthetic.main.activity_add_task.*

class AddTaskActivity : BaseActivity<AddTaskContract.Presenter, AddTaskContract.View>(),
        AddTaskContract.View, View.OnClickListener, Toolbar.OnMenuItemClickListener {

    override val view = this
    override fun createPresenter() = AddTaskPresenter()
    override fun getLayoutId() = R.layout.activity_add_task

    private val spinnerData = TaskStatusEnum.values().map { it.text }

    override fun initData() {
        getPresenterRoomModel()
        prepareActionBars()
        prepareSpinner()
        initListeners()
    }

    private fun initListeners() {
        fab_add_task.setOnClickListener(this)
    }

    private fun prepareSpinner() {
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerData)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_task.adapter = adapter
    }

    private fun prepareActionBars() {
        // bottom toolbar
        bottomAppBar_add_task.setOnMenuItemClickListener(this)
        presenter.checkDeleteIcon()

        //top toolbar
        setSupportActionBar(toolbar_add_task)
        supportActionBar?.run {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    /**
     * top toolbar
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_top_add_item_activity, menu)
        return true
    }

    /**
     * top toolbar
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            16908332 -> onBackPressed()
        }

        return true
    }

    /**
     * bottom toolbar
     */
    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_delete -> showDeleteDialog()
        }
        return true
    }

    private fun showDeleteDialog() {
        val dialog: OkCancelDialog = OkCancelDialog.newInstance(
                getString(R.string.delete_task_dialog_title),
                getString(R.string.delete_task_dialog_msg)
        )

        dialog.setLeftBtnListener(object : OkCancelDialog.OnLeftBtnClickListener {
            override fun btnClicked() {
                presenter.deleteItemFromDb()
            }
        })
        dialog.show(supportFragmentManager)
    }

    override fun showProgressBar() {
        fl_progress_bar_add_task.setVisible()
    }

    override fun hideProgressBar() {
        fl_progress_bar_add_task.setGone()
    }

    private fun getPresenterRoomModel() {
        val taskModel = intent.getParcelableExtra<TaskRoomModel>(ARG_TASK_MODEL)
        presenter.setRoomModel(taskModel)
    }

    override fun setTitle(title: String) {
        et_title_add_task.setText(title)
    }

    override fun setDescription(description: String) {
        et_description_add_task.setText(description)
        et_description_add_task.setSelection(description.length)
        et_description_add_task.requestFocus()
    }

    override fun enableDeleteBtn() {
        bottomAppBar_add_task.replaceMenu(R.menu.menu_bottom_add_item_activity)
    }

    override fun setStatusEnum(taskStatusEnum: TaskStatusEnum) {
        spinner_task.setSelection(TaskStatusEnum.values().indexOf(taskStatusEnum))
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab_add_task -> onSaveBtnClicked()
        }
    }

    private fun onSaveBtnClicked() {
        clearError()
        if (et_description_add_task.isDbDescriptionValid()) {
            val title = et_title_add_task.text
            val description = et_description_add_task.text
            presenter.saveModelInDb(title, description, spinner_task.selectedItem.toString())
        } else {
            et_description_add_task.error = getString(R.string.descr_error)
        }
    }

    private fun clearError() {
        et_description_add_task.error = null
    }

    override fun taskSaved() {
        onBackPressed()
    }

    companion object {

        private const val ARG_TASK_MODEL = "arg_task_model"

        fun getIntent(activity: Activity, taskModel: TaskRoomModel? = null): Intent {
            val intent = Intent(activity, AddTaskActivity::class.java)
            taskModel?.let { intent.putExtra(ARG_TASK_MODEL, taskModel) }

            return intent
        }
    }

}