package com.example.contentprovider.room.tasksTable

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.contentprovider.room.converters.TaskStatusEnum
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "task_room_table")
data class TaskRoomModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val taskTitle: String?,
    val taskDescription: String,
    val taskCreatedTime: Date,
    val taskStatusEnum: TaskStatusEnum
) : Parcelable