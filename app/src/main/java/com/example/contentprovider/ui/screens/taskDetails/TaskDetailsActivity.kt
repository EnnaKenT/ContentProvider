package com.example.contentprovider.ui.screens.taskDetails

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
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
import kotlinx.android.synthetic.main.activity_task_details.*

class TaskDetailsActivity : BaseActivity<TaskDetailsContract.Presenter, TaskDetailsContract.View>(),
        TaskDetailsContract.View, View.OnClickListener, Toolbar.OnMenuItemClickListener, () -> Unit {

    override val view = this
    override fun createPresenter() = TaskDetailsPresenter()
    override fun getLayoutId() = R.layout.activity_task_details

    private val spinnerData = TaskStatusEnum.values().map { it.text }

    override fun initData() {
        et_description_details_details.requestFocus()
        getPresenterRoomModel()
        prepareActionBars()
        prepareSpinner()
        initListeners()
    }

    private fun initListeners() {
        fab_task_details.setOnClickListener(this)
    }

    private fun prepareSpinner() {
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerData)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_task_details.adapter = adapter
    }

    private fun prepareActionBars() {
        // bottom toolbar
        bottomAppBar_task_details.setOnMenuItemClickListener(this)

        //top toolbar
        setSupportActionBar(toolbar_task_details)
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
            R.id.action_share -> shareTask()
        }
        return true
    }

    private fun shareTask() {
        startActivity(Intent.createChooser(presenter.getUriIntent(), getString(R.string.send_task)))
    }

    private fun showDeleteDialog() {
        val dialog: OkCancelDialog = OkCancelDialog.newInstance(
                getString(R.string.delete_task_dialog_title),
                getString(R.string.delete_task_dialog_msg),
                this
        )

        dialog.show(supportFragmentManager)
    }

    override fun invoke() {
        presenter.deleteItemFromDb()
    }

    override fun showProgressBar() {
        fl_progress_bar_task_details.setVisible()
    }

    override fun hideProgressBar() {
        fl_progress_bar_task_details.setGone()
    }

    private fun getPresenterRoomModel() {
        val taskModel = intent.getParcelableExtra<TaskRoomModel>(ARG_TASK_MODEL)
        val taskId = intent.getStringExtra(ARG_TASK_ID)?.toIntOrNull()
        presenter.setRoomModel(taskModel, taskId)
    }

    override fun setTitle(title: String) {
        et_title_task_details.setText(title)
    }

    override fun setDescription(description: String) {
        et_description_details_details.setText(description)
        et_description_details_details.setSelection(description.length)
    }

    override fun enableBottomBarBtns() {
        bottomAppBar_task_details.replaceMenu(R.menu.menu_bottom_item_details_activity)
    }

    override fun setStatusEnum(taskStatusEnum: TaskStatusEnum) {
        spinner_task_details.setSelection(TaskStatusEnum.values().indexOf(taskStatusEnum))
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab_task_details -> onSaveBtnClicked()
        }
    }

    private fun onSaveBtnClicked() {
        clearError()
        if (et_description_details_details.isDbDescriptionValid()) {
            val title = et_title_task_details.text
            val description = et_description_details_details.text
            presenter.saveModelInDb(title, description, spinner_task_details.selectedItem.toString())
        } else {
            et_description_details_details.error = getString(R.string.descr_error)
        }
    }

    private fun clearError() {
        et_description_details_details.error = null
    }

    override fun taskSaved() {
        onBackPressed()
    }

    companion object {

        private const val ARG_TASK_MODEL = "arg_task_model"
        private const val ARG_TASK_ID = "arg_task_id"

        fun getIntent(activity: Activity, taskModel: TaskRoomModel? = null, taskId: String? = null): Intent {
            return Intent(activity, TaskDetailsActivity::class.java).apply {
                putExtra(ARG_TASK_MODEL, taskModel)
                putExtra(ARG_TASK_ID, taskId)
            }
        }

        fun startActivity(activity: Activity, taskModel: TaskRoomModel? = null, taskId: String? = null) {
            activity.startActivity(getIntent(activity, taskModel, taskId))
        }
    }

}