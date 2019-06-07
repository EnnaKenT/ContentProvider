package com.example.contentprovider.screens.main.fragment.notes

import com.example.contentprovider.room.AppDatabase
import com.example.contentprovider.room.notesTable.NoteRoomModel
import com.example.contentprovider.screens.base.BasePresenter
import com.example.contentprovider.screens.main.fragment.TableFragmentContract
import kotlinx.coroutines.launch

class NotesFragmentPresenter : BasePresenter<TableFragmentContract.View<NoteRoomModel>>(),
    TableFragmentContract.Presenter<NoteRoomModel> {

    override fun prepareDatabaseModels() {
        launch {
            view?.showDatabaseModels(AppDatabase.initAppDataBase()?.noteRoomDao()?.getAllNotes())
        }
    }

}

