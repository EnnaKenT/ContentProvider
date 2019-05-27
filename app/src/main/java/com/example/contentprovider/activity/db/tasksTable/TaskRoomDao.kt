import androidx.room.*
import com.example.contentprovider.activity.db.converters.TaskStatusEnum
import java.util.*

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

    @Query("SELECT * FROM task_room_table WHERE taskName LIKE :taskName")
    suspend fun getTaskByName(taskName: String): List<TaskRoomModel>

    @Query("SELECT * FROM task_room_table WHERE taskDescription LIKE :taskDescription")
    suspend fun getTaskByDescr(taskDescription: String): List<TaskRoomModel>

    @Query("SELECT * FROM task_room_table WHERE taskCreatedTime LIKE :taskCreatedTime")
    suspend fun getTaskByCreatedTime(taskCreatedTime: Date): List<TaskRoomModel>

    @Query("SELECT * FROM task_room_table WHERE taskStatusEnum LIKE :taskStatusEnum")
    suspend fun getTaskByStatus(taskStatusEnum: TaskStatusEnum): List<TaskRoomModel>

}