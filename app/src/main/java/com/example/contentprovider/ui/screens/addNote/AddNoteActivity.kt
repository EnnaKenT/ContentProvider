package com.example.contentprovider.ui.screens.addNote

import android.app.Activity
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.example.contentprovider.R
import com.example.contentprovider.room.notesTable.NoteRoomModel
import com.example.contentprovider.ui.dialog.OkCancelDialog
import com.example.contentprovider.ui.screens.base.activity.BaseActivity
import com.example.contentprovider.utils.isDbDescriptionValid
import com.example.contentprovider.utils.setGone
import com.example.contentprovider.utils.setVisible
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : BaseActivity<AddNoteContract.Presenter, AddNoteContract.View>(),
        AddNoteContract.View, View.OnClickListener, Toolbar.OnMenuItemClickListener {

    override val view = this
    override fun createPresenter() = AddNotePresenter()
    override fun getLayoutId() = R.layout.activity_add_note

    override fun initData() {
        getPresenterRoomModel()
        prepareActionBars()
        initListeners()
    }

    private fun initListeners() {
        fab_add_note.setOnClickListener(this)
    }

    private fun getPresenterRoomModel() {
        val noteModel = intent.getParcelableExtra<NoteRoomModel>(ARG_NOTE_MODEL)
        presenter.setRoomModel(noteModel)
    }

    private fun prepareActionBars() {
        // bottom toolbar
        bottomAppBar_add_note.setOnMenuItemClickListener(this)
        presenter.checkDeleteIcon()

        //top toolbar
        setSupportActionBar(toolbar_add_note)
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

    override fun enableDeleteBtn() {
        bottomAppBar_add_note.replaceMenu(R.menu.menu_bottom_add_item_activity)
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
                getString(R.string.delete_note_dialog_msg)
        )

        dialog.setLeftBtnListener(object : OkCancelDialog.OnLeftBtnClickListener {
            override fun btnClicked() {
                presenter.deleteItemFromDb()
            }
        })
        dialog.show(supportFragmentManager)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab_add_note -> onSaveBtnClicked()
        }
    }

    private fun onSaveBtnClicked() {
        clearError()
        if (et_description_add_note.isDbDescriptionValid()) {
            val title = et_title_add_note.text
            val description = et_description_add_note.text
            presenter.saveModelInDb(title, description)
        } else {
            et_description_add_note.error = getString(R.string.descr_error)
        }
    }

    override fun setTitle(title: String) {
        et_title_add_note.setText(title)
    }

    override fun setDescription(description: String) {
        et_description_add_note.setText(description)
        et_description_add_note.setSelection(description.length)
    }

    private fun clearError() {
        et_description_add_note.error = null
    }

    override fun showProgressBar() {
        fl_progress_bar_add_note.setVisible()
    }

    override fun hideProgressBar() {
        fl_progress_bar_add_note.setGone()
    }

    override fun noteSaved() {
        onBackPressed()
    }

    companion object {

        private const val ARG_NOTE_MODEL = "arg_note_model"

        fun getIntent(activity: Activity, noteModel: NoteRoomModel? = null): Intent {
            val intent = Intent(activity, AddNoteActivity::class.java)
            noteModel?.let { intent.putExtra(ARG_NOTE_MODEL, noteModel) }

            return intent
        }

    }

}