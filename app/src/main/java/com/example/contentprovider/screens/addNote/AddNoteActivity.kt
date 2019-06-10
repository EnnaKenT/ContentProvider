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
        mbtn_save_add_item.setOnClickListener(this)
    }

    private fun getPresenterRoomModel() {
        noteModel = intent.getParcelableExtra(ARG_NOTE_MODEL)
        presenter.setModel(noteModel)
    }

    private fun prepareActionBar() {
        setSupportActionBar(toolbar)

        supportActionBar?.run {
            title = getString(R.string.note)
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