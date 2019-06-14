package com.example.contentprovider.room.notesTable

import android.content.ContentValues
import android.os.Parcelable
import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.contentprovider.room.notesTable.NoteRoomModel.Companion.TABLE_NAME
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TABLE_NAME)
data class NoteRoomModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    var id: Long? = null,
    @ColumnInfo(name = COLUMN_TITLE)
    val title: String?,
    @ColumnInfo(name = COLUMN_DESCRIPTION)
    val description: String
) : Parcelable {

    companion object {
        const val TABLE_NAME = "notes"
        const val COLUMN_ID = BaseColumns._ID
        const val COLUMN_TITLE = "title"
        const val COLUMN_DESCRIPTION = "description"

        fun fromContentValues(values: ContentValues): NoteRoomModel? {
            var id: Long? = null
            var title: String? = null
            var description: String? = null

            if (values.containsKey(COLUMN_ID)) {
                id = values.getAsLong(COLUMN_ID)
            }
            if (values.containsKey(COLUMN_TITLE)) {
                title = values.getAsString(COLUMN_TITLE)
            }
            if (values.containsKey(COLUMN_DESCRIPTION)) {
                description = values.getAsString(COLUMN_DESCRIPTION)
            }

            description?.let {
                return NoteRoomModel(id, title, description)
            }
            return null
        }
    }
}