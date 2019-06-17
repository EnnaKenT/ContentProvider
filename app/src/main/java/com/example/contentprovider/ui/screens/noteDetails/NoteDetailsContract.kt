package com.example.contentprovider.ui.screens.noteDetails

import android.content.Intent
import android.text.Editable
import com.example.contentprovider.room.notesTable.NoteRoomModel
import com.example.contentprovider.ui.screens.base.BaseContract

class NoteDetailsContract {

    interface Presenter : BaseContract.BasePresenter<View> {
        fun setRoomModel(noteModel: NoteRoomModel?, noteId: Int?)
        fun getRoomModel(): NoteRoomModel?
        fun saveModelInDb(title: Editable?, description: Editable)
        fun deleteItemFromDb()
        fun getUriIntent(): Intent
    }

    interface View : BaseContract.BaseView {
        fun noteSaved()
        fun showProgressBar()
        fun hideProgressBar()
        fun setTitle(title: String)
        fun setDescription(description: String)
        fun enableBottomBarBtns()
    }

}