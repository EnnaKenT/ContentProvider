package com.example.contentprovider.ui.screens.main.adapter.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contentprovider.R
import com.example.contentprovider.room.notesTable.NoteRoomModel
import com.example.contentprovider.utils.setGone
import com.example.contentprovider.utils.setVisible
import kotlinx.android.synthetic.main.item_table_note.view.*

class NotesTableAdapter(private val listener: (NoteRoomModel) -> Unit) :
        RecyclerView.Adapter<NotesTableAdapter.NotesViewHolder>() {

    private var notesList = mutableListOf<NoteRoomModel>()

    fun setItems(mutableList: MutableList<NoteRoomModel>) {
        notesList.clear()
        notesList.addAll(mutableList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_table_note, parent, false)
        return NotesViewHolder(view)
    }

    override fun getItemCount() = notesList.count()

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) =
            holder.bind(notesList[position], listener)

    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(noteModel: NoteRoomModel, listener: (NoteRoomModel) -> Unit) =
                with(itemView) {
                    if (noteModel.noteTitle.isNullOrEmpty()) {
                        tv_title.setGone()
                    } else {
                        tv_title.text = noteModel.noteTitle
                        tv_title.setVisible()
                    }

                    tv_description.text = noteModel.noteDescription

                    setOnClickListener { listener(noteModel) }
                }
    }
}