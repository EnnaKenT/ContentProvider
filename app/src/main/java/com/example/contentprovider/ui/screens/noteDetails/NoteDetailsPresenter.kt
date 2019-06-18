package com.example.contentprovider.ui.screens.noteDetails

import android.content.Intent
import android.text.Editable
import com.example.contentprovider.room.AppDatabase
import com.example.contentprovider.room.notesTable.NoteRoomModel
import com.example.contentprovider.ui.screens.base.BasePresenter
import com.example.contentprovider.utils.getNoteShareIntent
import kotlinx.coroutines.launch

class NoteDetailsPresenter : BasePresenter<NoteDetailsContract.View>(), NoteDetailsContract.Presenter {

    private var noteModel: NoteRoomModel? = null
    private var noteId: Int? = null

    override fun setRoomModel(noteModel: NoteRoomModel?, noteId: Int?) {
        this.noteModel = noteModel
        this.noteId = noteId

        if (noteModel == null && noteId != null) {
            getModelById()
        } else {
            checkExistModel()
        }
    }

    private fun getModelById() {
        launch {
            view?.showProgressBar()
            val noteModel = AppDatabase.getAppDataBase()?.noteRoomDao()?.getNoteById(noteId!!)
            setRoomModel(noteModel, noteId)
            view?.hideProgressBar()
        }
    }

    private fun checkExistModel() {
        noteModel?.title?.let { view?.setTitle(it) }
        noteModel?.description?.let {
            view?.setDescription(it)
            view?.enableBottomBarBtns()
        }
    }

    override fun getUriIntent(): Intent {
        return getNoteShareIntent(noteModel)
    }

    override fun deleteItemFromDb() {
        noteModel?.let {
            view?.showProgressBar()

            launch {
                AppDatabase.getAppDataBase()?.noteRoomDao()?.deleteNote(it)

                view?.hideProgressBar()
                view?.noteSaved()
            }
        }
    }

    override fun getRoomModel() = noteModel

    override fun saveModelInDb(title: Editable?, description: Editable) {
        view?.showProgressBar()

        val newNoteModel = NoteRoomModel(
                noteModel?.id,
                title.toString(),
                description.toString()
        )

        launch {
            AppDatabase.getAppDataBase()?.noteRoomDao()?.insertNote(newNoteModel)
            view?.hideProgressBar()
            view?.noteSaved()
        }
    }
}

