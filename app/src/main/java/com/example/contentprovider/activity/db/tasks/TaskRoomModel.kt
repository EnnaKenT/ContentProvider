import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "task_room_table", indices = [Index(value = ["task_id"], unique = true)])
data class TaskRoomModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "task_id")
    val taskId: String,
    @ColumnInfo(name = "viewed_timestamp")
    val viewedTimestamp: Long
)