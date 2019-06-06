package com.example.contentprovider.room.notesTable

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_room_table")
data class NoteRoomModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val noteName: String,
    val noteContent: String
)