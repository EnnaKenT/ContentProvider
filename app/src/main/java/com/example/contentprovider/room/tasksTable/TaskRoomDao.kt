package com.example.contentprovider.room.tasksTable

import android.database.Cursor
import androidx.room.*
import com.example.contentprovider.room.converters.TaskStatusEnum
import java.util.*

@Dao
interface TaskRoomDao {

    @Query("SELECT * FROM ${TaskRoomModel.TABLE_NAME}")
    suspend fun getAllTasks(): MutableList<TaskRoomModel>

    @Query("SELECT * FROM ${TaskRoomModel.TABLE_NAME}")
    fun getAllTasksAsCursor(): Cursor

    @Query("SELECT * FROM ${TaskRoomModel.TABLE_NAME} WHERE ${TaskRoomModel.COLUMN_ID} LIKE :id")
    fun getTaskByIdAsCursor(id: Long): Cursor

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(vararg taskModels: TaskRoomModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(tasks: TaskRoomModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTaskFromProvider(tasks: TaskRoomModel): Long

    @Update
    suspend fun updateTask(taskModel: TaskRoomModel)

    @Update
    fun updateTaskFromProvider(taskModel: TaskRoomModel): Int

    @Delete
    suspend fun deleteTask(taskModel: TaskRoomModel)

//    @Delete
//    fun deleteTaskById(id: Long): Int

    @Query("SELECT * FROM ${TaskRoomModel.TABLE_NAME} WHERE ${TaskRoomModel.COLUMN_TITLE} LIKE :taskTitle")
    suspend fun getTaskByName(taskTitle: String): MutableList<TaskRoomModel>

    @Query("SELECT * FROM ${TaskRoomModel.TABLE_NAME} WHERE ${TaskRoomModel.COLUMN_DESCRIPTION} LIKE :taskDescription")
    suspend fun getTaskByDescr(taskDescription: String): MutableList<TaskRoomModel>

    @Query("SELECT * FROM ${TaskRoomModel.TABLE_NAME} WHERE ${TaskRoomModel.COLUMN_CREATED_TIME} LIKE :taskCreatedTime")
    suspend fun getTaskByCreatedTime(taskCreatedTime: Date): MutableList<TaskRoomModel>

    @Query("SELECT * FROM ${TaskRoomModel.TABLE_NAME} WHERE ${TaskRoomModel.COLUMN_TASK_STATUS} LIKE :taskStatusEnum")
    suspend fun getTaskByStatus(taskStatusEnum: TaskStatusEnum): MutableList<TaskRoomModel>

}