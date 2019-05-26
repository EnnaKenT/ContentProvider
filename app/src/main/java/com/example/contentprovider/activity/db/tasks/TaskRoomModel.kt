import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.contentprovider.activity.db.cooverters.TaskStatusEnum
import java.util.*

@Entity(tableName = "task_room_table")
data class TaskRoomModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val taskName: String,
    val taskDescription: String,
    val taskCreatedTime: Date,
    val taskStatusEnum: TaskStatusEnum
)