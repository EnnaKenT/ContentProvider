package com.example.contentprovider.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.contentprovider.room.converters.DateTypeConverter
import com.example.contentprovider.room.converters.TaskStatusTypeConverter
import com.example.contentprovider.room.notesTable.NoteRoomDao
import com.example.contentprovider.room.notesTable.NoteRoomModel
import com.example.contentprovider.room.tasksTable.TaskRoomDao
import com.example.contentprovider.room.tasksTable.TaskRoomModel

@Database(entities = [TaskRoomModel::class, NoteRoomModel::class], version = 1)
@TypeConverters(DateTypeConverter::class, TaskStatusTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskRoomDao(): TaskRoomDao
    abstract fun noteRoomDao(): NoteRoomDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private var DB_NAME: String = "content.provider.database"

        fun initAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room
                        .databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                        .build()
                }
            }
            return INSTANCE
        }

        fun getAppDataBase(): AppDatabase? {
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}