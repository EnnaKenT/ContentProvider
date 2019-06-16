package com.example.contentprovider.provider

import android.app.SearchManager
import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.provider.BaseColumns
import com.example.contentprovider.BuildConfig
import com.example.contentprovider.room.AppDatabase

class MyContentProvider : ContentProvider() {

    companion object {

        /** The authority of this content provider.  */
        const val AUTHORITY = BuildConfig.PROVIDER_AUTHORITY_FIELD

        private const val SEARCH = SearchManager.SUGGEST_URI_PATH_QUERY + "/*"

        private val SEARCH_SUGGEST_COLUMNS = arrayOf(
            BaseColumns._ID,
            SearchManager.SUGGEST_COLUMN_TEXT_1,
            SearchManager.SUGGEST_COLUMN_TEXT_2,
            SearchManager.SUGGEST_COLUMN_INTENT_DATA
        )
        private const val SEARCH_CODE = 1

        /** The URI matcher.  */
        private val MATCHER = UriMatcher(UriMatcher.NO_MATCH)

        init {
            MATCHER.addURI(AUTHORITY, SEARCH, SEARCH_CODE)
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
        if (code == SEARCH_CODE) {
            val cursor = MatrixCursor(SEARCH_SUGGEST_COLUMNS)
            val query = uri.lastPathSegment?.toLowerCase()

            val notes = AppDatabase.initAppDataBase(context!!)?.noteRoomDao()?.getNoteByLetter(query!!)
            val tasks = AppDatabase.initAppDataBase(context!!)?.taskRoomDao()?.getTasksByLetter(query!!)
            if (!notes.isNullOrEmpty()) {
                notes.forEach { cursor.addRow(arrayOf(it.id, it.title, it.description, it)) }
            }
            if (!tasks.isNullOrEmpty()) {
                tasks.forEach { cursor.addRow(arrayOf(it.id, it.title, it.description, it)) }
            }
            return cursor
        }
        return null
    }

    override fun getType(uri: Uri): String? {
        return when (MATCHER.match(uri)) {
            1 -> "vnd.android.cursor.item/$AUTHORITY.$SEARCH"
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    /**
     * not permitted yet in manifest
     */
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        throw UnsupportedOperationException()
    }

    /**
     * not permitted yet in manifest
     */
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        throw UnsupportedOperationException()
    }

    /**
     * not permitted yet in manifest
     */
    override fun update(
        uri: Uri, values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        throw UnsupportedOperationException()
    }
}