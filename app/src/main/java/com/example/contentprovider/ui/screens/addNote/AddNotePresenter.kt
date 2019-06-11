package com.example.contentprovider.ui.screens.addNote

import android.text.Editable
import com.example.contentprovider.room.AppDatabase
import com.example.contentprovider.room.notesTable.NoteRoomModel
import com.example.contentprovider.ui.screens.base.BasePresenter
import kotlinx.coroutines.launch

class AddNotePresenter : BasePresenter<AddNoteContract.View>(), AddNoteContract.Presenter {
    private var noteModel: NoteRoomModel? = null

    override fun setRoomModel(noteModel: NoteRoomModel?) {
        this.noteModel = noteModel
        checkExistModel()
    }

    private fun checkExistModel() {
        noteModel?.noteTitle?.let { view?.setTitle(it) }
        noteModel?.noteDescription?.let {
            view?.setDescription(it)
        }
    }

    override fun checkDeleteIcon() {
        noteModel?.noteDescription?.let {
            view?.enableDeleteBtn()
        }
    }

    override fun deleteItemFromDb() {
        noteModel?.let {
            view?.showProgressBar()

            launch {
                AppDatabase.initAppDataBase()?.noteRoomDao()?.deleteNote(it)

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
            AppDatabase.initAppDataBase()?.noteRoomDao()?.insertNote(newNoteModel)
            view?.hideProgressBar()
            view?.noteSaved()
        }
    }
}

