import androidx.room.*

@Dao
interface TaskRoomDao {

    @Query("SELECT * FROM task_room_table")
    suspend fun getAllTasks(): List<TaskRoomModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(vararg taskModels: TaskRoomModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(tasks: TaskRoomModel)

    @Update
    suspend fun updateTask(taskModel: TaskRoomModel)

    @Delete
    suspend fun deleteTask(taskModel: TaskRoomModel)

    // get all cars with specific condition
    @Query("SELECT * FROM task_room_table WHERE task_id LIKE :taskId")
    suspend fun getTaskById(taskId: String): List<TaskRoomModel>

}