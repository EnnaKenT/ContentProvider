package com.example.contentprovider.room.notesTable

import androidx.room.*

@Dao
interface NoteRoomDao {

    @Query("SELECT * FROM note_room_table")
    suspend fun getAllNotes(): MutableList<NoteRoomModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(vararg noteModels: NoteRoomModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(notes: NoteRoomModel)

    @Update
    suspend fun updateNote(noteModel: NoteRoomModel)

    @Delete
    suspend fun deleteNote(noteModel: NoteRoomModel)

    @Query("SELECT * FROM note_room_table WHERE noteTitle LIKE :noteTitle")
    suspend fun getNoteByTitle(noteTitle: String): MutableList<NoteRoomModel>

    @Query("SELECT * FROM note_room_table WHERE noteDescription LIKE :noteDescription")
    suspend fun getNoteByDescription(noteDescription: String): MutableList<NoteRoomModel>

}