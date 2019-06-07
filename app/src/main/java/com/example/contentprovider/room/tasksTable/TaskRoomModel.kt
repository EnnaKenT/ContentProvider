package com.example.contentprovider.room.tasksTable

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.contentprovider.room.converters.TaskStatusEnum
import java.util.*

@Entity(tableName = "task_room_table")
data class TaskRoomModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val taskTitle: String?,
    val taskDescription: String,
    val taskCreatedTime: Date,
    val taskStatusEnum: TaskStatusEnum
)