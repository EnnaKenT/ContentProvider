package com.example.contentprovider.ui.screens.addNote

import android.text.Editable
import com.example.contentprovider.room.notesTable.NoteRoomModel
import com.example.contentprovider.ui.screens.base.BaseContract

class NoteDetailsContract {

    interface Presenter : BaseContract.BasePresenter<View> {
        fun setRoomModel(noteModel: NoteRoomModel?)
        fun getRoomModel(): NoteRoomModel?
        fun saveModelInDb(title: Editable?, description: Editable)
        fun checkDeleteIcon()
        fun deleteItemFromDb()
    }

    interface View : BaseContract.BaseView {
        fun noteSaved()
        fun showProgressBar()
        fun hideProgressBar()
        fun setTitle(title: String)
        fun setDescription(description: String)
        fun enableDeleteBtn()
    }

}