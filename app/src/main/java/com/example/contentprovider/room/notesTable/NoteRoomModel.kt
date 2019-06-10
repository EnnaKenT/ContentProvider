package com.example.contentprovider.room.notesTable

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "note_room_table")
data class NoteRoomModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val noteTitle: String?,
    val noteDescription: String
) : Parcelable