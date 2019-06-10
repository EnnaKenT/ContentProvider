package com.example.contentprovider.screens.addNote

import android.app.Activity
import android.content.Intent
import android.view.View
import com.example.contentprovider.R
import com.example.contentprovider.room.notesTable.NoteRoomModel
import com.example.contentprovider.screens.base.activity.BaseActivity
import com.example.contentprovider.utils.isDbDescriptionValid
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : BaseActivity<AddNoteContract.Presenter, AddNoteContract.View>(),
        AddNoteContract.View, View.OnClickListener {
    private var noteModel: NoteRoomModel? = null

    override val view = this

    override fun createPresenter() = AddNotePresenter()
    override fun getLayoutId() = R.layout.activity_add_note

    override fun initData() {
        getPresenterRoomModel()
        prepareActionBar()
        mbtn_save_add_note.setOnClickListener(this)
    }

    private fun getPresenterRoomModel() {
        noteModel = intent.getParcelableExtra(ARG_NOTE_MODEL)
        presenter.setModel(noteModel)
    }

    private fun prepareActionBar() {
        setSupportActionBar(toolbar_add_note)

        supportActionBar?.run {
            title = getString(R.string.note)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mbtn_save_add_task -> onSaveBtnClicked()
        }
    }

    private fun onSaveBtnClicked() {
        et_description_add_note.error = ""
        if (et_description_add_note.isDbDescriptionValid()) {
            val title = et_title_add_note.text
            val description = et_description_add_note.text
            presenter.saveModel(title, description)
        } else {
            et_description_add_note.error = getString(R.string.descr_error)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (noteModel == null) {
            overridePendingTransition(R.anim.slide_from_top, R.anim.slide_to_bottom)
        } else {
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }
    }

    companion object {

        private const val ARG_NOTE_MODEL = "arg_note_model"

        fun startActivity(activity: Activity, noteModel: NoteRoomModel? = null) {
            val intent = Intent(activity, AddNoteActivity::class.java)
            noteModel?.let { intent.putExtra(ARG_NOTE_MODEL, noteModel) }

            activity.startActivity(intent)
        }

    }

}