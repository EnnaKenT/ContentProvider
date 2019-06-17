package com.example.contentprovider.room.tasksTable

import androidx.room.*
import com.example.contentprovider.room.converters.TaskStatusEnum
import java.util.*

@Dao
interface TaskRoomDao {

    @Query("SELECT * FROM ${TaskRoomModel.TABLE_NAME}")
    suspend fun getAllTasks(): MutableList<TaskRoomModel>

    @Query("SELECT * FROM ${TaskRoomModel.TABLE_NAME} " +
            "WHERE ${TaskRoomModel.COLUMN_TITLE} LIKE '%' || :argLetter || '%' " +
            "OR ${TaskRoomModel.COLUMN_DESCRIPTION} LIKE '%' || :argLetter || '%'")
    fun getTasksByLetter(argLetter: String): MutableList<TaskRoomModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(vararg taskModels: TaskRoomModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(tasks: TaskRoomModel)

    @Update
    suspend fun updateTask(taskModel: TaskRoomModel)

    @Delete
    suspend fun deleteTask(taskModel: TaskRoomModel)

    @Query("SELECT * FROM ${TaskRoomModel.TABLE_NAME} WHERE ${TaskRoomModel.COLUMN_ID} LIKE :id")
    suspend fun getTaskById(id: Int): TaskRoomModel

    @Query("SELECT * FROM ${TaskRoomModel.TABLE_NAME} WHERE ${TaskRoomModel.COLUMN_TITLE} LIKE :taskTitle")
    suspend fun getTaskByName(taskTitle: String): MutableList<TaskRoomModel>

    @Query("SELECT * FROM ${TaskRoomModel.TABLE_NAME} WHERE ${TaskRoomModel.COLUMN_DESCRIPTION} LIKE :taskDescription")
    suspend fun getTaskByDescr(taskDescription: String): MutableList<TaskRoomModel>

    @Query("SELECT * FROM ${TaskRoomModel.TABLE_NAME} WHERE ${TaskRoomModel.COLUMN_CREATED_TIME} LIKE :taskCreatedTime")
    suspend fun getTaskByCreatedTime(taskCreatedTime: Date): MutableList<TaskRoomModel>

    @Query("SELECT * FROM ${TaskRoomModel.TABLE_NAME} WHERE ${TaskRoomModel.COLUMN_TASK_STATUS} LIKE :taskStatusEnum")
    suspend fun getTaskByStatus(taskStatusEnum: TaskStatusEnum): MutableList<TaskRoomModel>

}