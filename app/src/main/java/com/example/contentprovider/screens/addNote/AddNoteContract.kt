package com.example.contentprovider.screens.addNote

import android.text.Editable
import android.widget.EditText
import com.example.contentprovider.room.notesTable.NoteRoomModel
import com.example.contentprovider.screens.base.BaseContract

class AddNoteContract {

    interface Presenter : BaseContract.BasePresenter<View> {
        fun saveModel(title: Editable?, description: EditText?)

        fun setModel(noteModel: NoteRoomModel?)
    }

    interface View : BaseContract.BaseView {
    }

}