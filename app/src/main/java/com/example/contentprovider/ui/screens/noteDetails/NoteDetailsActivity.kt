package com.example.contentprovider.ui.screens.noteDetails

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
import kotlinx.android.synthetic.main.activity_note_details.*

class NoteDetailsActivity : BaseActivity<NoteDetailsContract.Presenter, NoteDetailsContract.View>(),
        NoteDetailsContract.View, View.OnClickListener, Toolbar.OnMenuItemClickListener, () -> Unit {

    override val view = this
    override fun createPresenter() = NoteDetailsPresenter()
    override fun getLayoutId() = R.layout.activity_note_details

    override fun initData() {
        et_description_note_details.requestFocus()
        getPresenterRoomModel()
        prepareActionBars()
        initListeners()
    }

    private fun initListeners() {
        fab_note_details.setOnClickListener(this)
    }

    private fun getPresenterRoomModel() {
        val noteModel = intent.getParcelableExtra<NoteRoomModel>(ARG_NOTE_MODEL)
        val noteId = intent.getStringExtra(ARG_NOTE_ID)?.toIntOrNull()
        presenter.setRoomModel(noteModel, noteId)
    }

    private fun prepareActionBars() {
        // bottom toolbar
        bottomAppBar_note_details.setOnMenuItemClickListener(this)

        //top toolbar
        setSupportActionBar(toolbar_note_details)
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

    override fun enableBottomBarBtns() {
        bottomAppBar_note_details.replaceMenu(R.menu.menu_bottom_item_details_activity)
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
            R.id.action_share -> shareNote()
        }
        return true
    }

    private fun shareNote() {
        startActivity(Intent.createChooser(presenter.getUriIntent(), getString(R.string.send_note)))
    }

    private fun showDeleteDialog() {
        val dialog: OkCancelDialog = OkCancelDialog.newInstance(
                getString(R.string.delete_task_dialog_title),
                getString(R.string.delete_note_dialog_msg),
                this
        )

        dialog.show(supportFragmentManager)
    }

    override fun invoke() {
        presenter.deleteItemFromDb()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab_note_details -> onSaveBtnClicked()
        }
    }

    private fun onSaveBtnClicked() {
        clearError()
        if (et_description_note_details.isDbDescriptionValid()) {
            val title = et_title_note_details.text
            val description = et_description_note_details.text
            presenter.saveModelInDb(title, description)
        } else {
            et_description_note_details.error = getString(R.string.descr_error)
        }
    }

    override fun setTitle(title: String) {
        et_title_note_details.setText(title)
    }

    override fun setDescription(description: String) {
        et_description_note_details.setText(description)
        et_description_note_details.setSelection(description.length)
    }

    private fun clearError() {
        et_description_note_details.error = null
    }

    override fun showProgressBar() {
        fl_progress_bar_note_details.setVisible()
    }

    override fun hideProgressBar() {
        fl_progress_bar_note_details.setGone()
    }

    override fun noteSaved() {
        onBackPressed()
    }

    companion object {

        private const val ARG_NOTE_MODEL = "arg_note_model"
        private const val ARG_NOTE_ID = "arg_note_id"

        fun getIntent(activity: Activity, noteModel: NoteRoomModel? = null, noteId: String? = null): Intent {
            return Intent(activity, NoteDetailsActivity::class.java).apply {
                putExtra(ARG_NOTE_MODEL, noteModel)
                putExtra(ARG_NOTE_ID, noteId)
            }
        }

        fun startActivity(activity: Activity, noteModel: NoteRoomModel? = null, noteId: String? = null) {
            activity.startActivity(getIntent(activity, noteModel, noteId))
        }

    }

}