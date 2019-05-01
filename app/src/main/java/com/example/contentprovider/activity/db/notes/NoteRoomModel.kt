import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "note_room_table", indices = [Index(value = ["note_id"], unique = true)])
data class NoteRoomModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "note_id")
    val noteId: String,
    @ColumnInfo(name = "viewed_timestamp")
    val viewedTimestamp: Long
)