import androidx.room.TypeConverter
import com.example.contentprovider.activity.db.converters.TaskStatusEnum

class TaskStatusTypeConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun taskStatusToInt(enum: TaskStatusEnum): Int {
            return enum.ordinal
        }

        @TypeConverter
        @JvmStatic
        fun intToTaskStatus(i: Int): TaskStatusEnum? {
            return TaskStatusEnum.values().firstOrNull { it.ordinal == i }
        }
    }
}