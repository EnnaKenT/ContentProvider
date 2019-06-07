package com.example.contentprovider.room.tasksTable

import androidx.room.*
import com.example.contentprovider.room.converters.TaskStatusEnum
import java.util.*

@Dao
interface TaskRoomDao {

    @Query("SELECT * FROM task_room_table")
    suspend fun getAllTasks(): MutableList<TaskRoomModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(vararg taskModels: TaskRoomModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(tasks: TaskRoomModel)

    @Update
    suspend fun updateTask(taskModel: TaskRoomModel)

    @Delete
    suspend fun deleteTask(taskModel: TaskRoomModel)

    @Query("SELECT * FROM task_room_table WHERE taskTitle LIKE :taskTitle")
    suspend fun getTaskByName(taskTitle: String): MutableList<TaskRoomModel>

    @Query("SELECT * FROM task_room_table WHERE taskDescription LIKE :taskDescription")
    suspend fun getTaskByDescr(taskDescription: String): MutableList<TaskRoomModel>

    @Query("SELECT * FROM task_room_table WHERE taskCreatedTime LIKE :taskCreatedTime")
    suspend fun getTaskByCreatedTime(taskCreatedTime: Date): MutableList<TaskRoomModel>

    @Query("SELECT * FROM task_room_table WHERE taskStatusEnum LIKE :taskStatusEnum")
    suspend fun getTaskByStatus(taskStatusEnum: TaskStatusEnum): MutableList<TaskRoomModel>

}