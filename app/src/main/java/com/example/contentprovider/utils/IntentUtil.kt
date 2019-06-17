package com.example.contentprovider.utils

import android.content.Intent
import com.example.contentprovider.room.notesTable.NoteRoomModel
import com.example.contentprovider.room.tasksTable.TaskRoomModel

fun Intent.getTaskShareIntent(taskModel: TaskRoomModel?): Intent {
    val body = "${taskModel?.title}\n\n" +
            "${taskModel?.description}\n\n" +
            "${taskModel?.taskStatusEnum?.text}\n" +
            "${taskModel?.createdTime?.roomModelTimeToString()}"

    return Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, body)
    }
}

fun Intent.getNoteShareIntent(noteModel: NoteRoomModel?): Intent {
    val body = "${noteModel?.title}\n\n" +
            "${noteModel?.description}"
    return Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, body)
    }
}