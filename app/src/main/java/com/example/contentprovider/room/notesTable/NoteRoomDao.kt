package com.example.contentprovider.room.notesTable

import android.database.Cursor
import androidx.room.*

@Dao
interface NoteRoomDao {

    @Query("SELECT * FROM ${NoteRoomModel.TABLE_NAME}")
    suspend fun getAllNotes(): MutableList<NoteRoomModel>

    @Query("SELECT * FROM ${NoteRoomModel.TABLE_NAME} " +
            "WHERE ${NoteRoomModel.COLUMN_TITLE} LIKE '%' || :argLetter || '%' " +
            "OR ${NoteRoomModel.COLUMN_DESCRIPTION} LIKE '%' || :argLetter || '%'")
    fun getNoteByLetter(argLetter: String): MutableList<NoteRoomModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(vararg noteModels: NoteRoomModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(notes: NoteRoomModel)

    @Update
    suspend fun updateNote(noteModel: NoteRoomModel)

    @Delete
    suspend fun deleteNote(noteModel: NoteRoomModel)

//    @Delete
//    fun deleteNoteById(id: Long): Int

    @Query("SELECT * FROM ${NoteRoomModel.TABLE_NAME} WHERE ${NoteRoomModel.COLUMN_TITLE} LIKE :noteTitle")
    suspend fun getNoteByTitle(noteTitle: String): MutableList<NoteRoomModel>

    @Query("SELECT * FROM ${NoteRoomModel.TABLE_NAME} WHERE ${NoteRoomModel.COLUMN_DESCRIPTION} LIKE :noteDescription")
    suspend fun getNoteByDescription(noteDescription: String): MutableList<NoteRoomModel>

}