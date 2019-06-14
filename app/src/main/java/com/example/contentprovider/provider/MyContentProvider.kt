package com.example.contentprovider.provider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.contentprovider.BuildConfig
import com.example.contentprovider.room.AppDatabase
import com.example.contentprovider.room.notesTable.NoteRoomModel
import com.example.contentprovider.room.tasksTable.TaskRoomModel

class MyContentProvider : ContentProvider() {

    companion object {

        /** The authority of this content provider.  */
        const val AUTHORITY = BuildConfig.PROVIDER_AUTHORITY

        /** The URI for the Tasks table.  */
        val URI_TASKS = Uri.parse("content://" + AUTHORITY + "/" + TaskRoomModel.TABLE_NAME)
        /** The match code for some items in the Tasks table.  */
        private const val CODE_TASKS_DIR = 3
        /** The match code for an item in the Tasks table.  */
        private const val CODE_TASKS_ITEM = 4

        /** The URI for the Notes table.  */
        val URI_NOTES = Uri.parse("content://" + AUTHORITY + "/" + NoteRoomModel.TABLE_NAME)
        /** The match code for some items in the Notes table.  */
        private const val CODE_NOTES_DIR = 1
        /** The match code for an item in the Notes table.  */
        private const val CODE_NOTES_ITEM = 2

        /** The URI matcher.  */
        private val MATCHER = UriMatcher(UriMatcher.NO_MATCH)

        init {
            MATCHER.addURI(AUTHORITY, NoteRoomModel.TABLE_NAME, CODE_NOTES_DIR)
            MATCHER.addURI(AUTHORITY, NoteRoomModel.TABLE_NAME + "/*", CODE_NOTES_ITEM)
            MATCHER.addURI(AUTHORITY, TaskRoomModel.TABLE_NAME, CODE_TASKS_DIR)
            MATCHER.addURI(AUTHORITY, TaskRoomModel.TABLE_NAME + "/*", CODE_TASKS_ITEM)
        }

    }

    override fun onCreate() = true

    override fun query(
        uri: Uri, projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        val code = MATCHER.match(uri)

        if (code == CODE_NOTES_DIR || code == CODE_NOTES_ITEM) {
            context?.run {
                val notes = AppDatabase.initAppDataBase(this)?.noteRoomDao()
                val cursor = if (code == CODE_NOTES_DIR) {
                    notes?.getAllNotesAsCursor()
                } else {
                    notes?.getNoteByIdAsCursor(ContentUris.parseId(uri))
                }

                return cursor?.setNotificationUri(contentResolver, uri) as Cursor
            }
            return null
        } else if (code == CODE_TASKS_DIR || code == CODE_TASKS_ITEM) {
            context?.run {
                val tasks = AppDatabase.initAppDataBase(this)?.taskRoomDao()
                val cursor = if (code == CODE_TASKS_DIR) {
                    tasks?.getAllTasksAsCursor()
                } else {
                    tasks?.getTaskByIdAsCursor(ContentUris.parseId(uri))
                }

                return cursor?.setNotificationUri(contentResolver, uri) as Cursor

            }
            return null
        } else {
            throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun getType(uri: Uri): String? {
        return when (MATCHER.match(uri)) {
            CODE_NOTES_DIR -> "vnd.android.cursor.dir/$AUTHORITY.${NoteRoomModel.TABLE_NAME}"
            CODE_NOTES_ITEM -> "vnd.android.cursor.item/$AUTHORITY.${NoteRoomModel.TABLE_NAME}"
            CODE_TASKS_DIR -> "vnd.android.cursor.dir/$AUTHORITY.${TaskRoomModel.TABLE_NAME}"
            CODE_TASKS_ITEM -> "vnd.android.cursor.item/$AUTHORITY.${TaskRoomModel.TABLE_NAME}"
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    /**
     * not permitted yet in manifest
     */
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        when (MATCHER.match(uri)) {
            CODE_NOTES_DIR -> {
                if (values == null || context == null) {
                    return null
                }
                val newNote = NoteRoomModel.fromContentValues(values) ?: return null
                val id = AppDatabase.initAppDataBase(context!!)?.noteRoomDao()?.insertNoteFromProvider(newNote)
                    ?: return null
                context!!.contentResolver.notifyChange(uri, null)
                return ContentUris.withAppendedId(uri, id)
            }
            CODE_NOTES_ITEM -> throw IllegalArgumentException("Invalid URI, cannot insert with ID: $uri")
            CODE_TASKS_DIR -> {
                if (values == null || context == null) {
                    return null
                }
                val newTask = TaskRoomModel.fromContentValues(values) ?: return null
                val id = AppDatabase.initAppDataBase(context!!)?.taskRoomDao()?.insertTaskFromProvider(newTask)
                    ?: return null
                context!!.contentResolver.notifyChange(uri, null)
                return ContentUris.withAppendedId(uri, id)
            }
            CODE_TASKS_ITEM -> throw IllegalArgumentException("Invalid URI, cannot insert with ID: $uri")
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    /**
     * not permitted yet in manifest
     */
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        when (MATCHER.match(uri)) {
            CODE_NOTES_DIR -> throw IllegalArgumentException("Invalid URI, cannot delete without ID$uri")
            CODE_NOTES_ITEM -> {
                if (context == null) {
                    return 0
                }

                val count =
                    AppDatabase.initAppDataBase(context!!)?.noteRoomDao()?.deleteNoteById(ContentUris.parseId(uri))
                context!!.contentResolver.notifyChange(uri, null)
                return count ?: 0
            }
            CODE_TASKS_DIR -> throw IllegalArgumentException("Invalid URI, cannot delete without ID$uri")
            CODE_TASKS_ITEM -> {
                if (context == null) {
                    return 0
                }

                val count =
                    AppDatabase.initAppDataBase(context!!)?.taskRoomDao()?.deleteTaskById(ContentUris.parseId(uri))
                context!!.contentResolver.notifyChange(uri, null)
                return count ?: 0
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    /**
     * not permitted yet in manifest
     */
    override fun update(
        uri: Uri, values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        when (MATCHER.match(uri)) {
            CODE_NOTES_DIR -> throw IllegalArgumentException("Invalid URI, cannot update without ID$uri")
            CODE_NOTES_ITEM -> {
                if (context == null || values == null) {
                    return 0
                }

                val note = NoteRoomModel.fromContentValues(values)
                if (note?.description == null) {
                    return 0
                }
                val newNote = NoteRoomModel(ContentUris.parseId(uri), note.title, note.description)

                val count = AppDatabase.initAppDataBase(context!!)?.noteRoomDao()?.updateNoteFromProvider(newNote)
                context!!.contentResolver.notifyChange(uri, null)
                return count ?: 0
            }
            CODE_TASKS_DIR -> throw IllegalArgumentException("Invalid URI, cannot update without ID$uri")
            CODE_TASKS_ITEM -> {
                if (context == null || values == null) {
                    return 0
                }

                val task = TaskRoomModel.fromContentValues(values)
                if (task?.description == null) {
                    return 0
                }
                val newTask = TaskRoomModel(
                    ContentUris.parseId(uri),
                    task.title,
                    task.description,
                    task.createdTime,
                    task.taskStatusEnum
                )

                val count = AppDatabase.initAppDataBase(context!!)?.taskRoomDao()?.updateTaskFromProvider(newTask)
                context!!.contentResolver.notifyChange(uri, null)
                return count ?: 0
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }
}