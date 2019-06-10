package com.example.contentprovider.screens.addNote

import android.text.Editable
import com.example.contentprovider.room.notesTable.NoteRoomModel
import com.example.contentprovider.screens.base.BaseContract

class AddNoteContract {

    interface Presenter : BaseContract.BasePresenter<View> {
        fun saveModel(title: Editable?, description: Editable)

        fun setModel(noteModel: NoteRoomModel?)
    }

    interface View : BaseContract.BaseView {
    }

}