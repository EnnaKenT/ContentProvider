package com.example.contentprovider.screens.addNote

import android.text.Editable
import android.widget.EditText
import com.example.contentprovider.room.notesTable.NoteRoomModel
import com.example.contentprovider.screens.base.BasePresenter

class AddNotePresenter : BasePresenter<AddNoteContract.View>(), AddNoteContract.Presenter {
    private var noteModel: NoteRoomModel? = null

    override fun setModel(noteModel: NoteRoomModel?) {
        this.noteModel = noteModel
    }

    override fun saveModel(title: Editable?, description: EditText?) {

    }
}

