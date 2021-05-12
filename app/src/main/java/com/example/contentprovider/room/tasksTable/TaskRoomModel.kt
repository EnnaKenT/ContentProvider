package com.example.contentprovider.room.tasksTable

import android.content.ContentValues
import android.os.Parcelable
import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.contentprovider.room.converters.TaskStatusEnum
import com.example.contentprovider.room.tasksTable.TaskRoomModel.Companion.TABLE_NAME
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = TABLE_NAME)
data class TaskRoomModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    var id: Long? = null,
    @ColumnInfo(name = COLUMN_TITLE)
    var title: String?,
    @ColumnInfo(name = COLUMN_DESCRIPTION)
    var description: String,
    @ColumnInfo(name = COLUMN_CREATED_TIME)
    val createdTime: Date,
    @ColumnInfo(name = COLUMN_TASK_STATUS)
    var taskStatusEnum: TaskStatusEnum
) : Parcelable {

    companion object {
        const val TABLE_NAME = "tasks"
        const val COLUMN_ID = BaseColumns._ID
        const val COLUMN_TITLE = "title"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_CREATED_TIME = "created_time"
        const val COLUMN_TASK_STATUS = "task_status"

        fun fromContentValues(values: ContentValues): TaskRoomModel? {
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
                return TaskRoomModel(
                    id,
                    title,
                    it,
                    Date(System.currentTimeMillis()),
                    TaskStatusEnum.IN_PROGRESS
                )
            }
            return null
        }
    }
}