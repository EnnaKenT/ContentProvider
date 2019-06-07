package com.example.contentprovider.screens.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contentprovider.R
import com.example.contentprovider.room.tasksTable.TaskRoomModel
import com.example.contentprovider.utils.roomModelTimeToString
import com.example.contentprovider.utils.setGone
import com.example.contentprovider.utils.setVisible
import kotlinx.android.synthetic.main.item_table_task.view.*

class TasksTableAdapter(private val listener: (TaskRoomModel) -> Unit) :
        RecyclerView.Adapter<TasksTableAdapter.TaskViewHolder>() {

    private var taskList = mutableListOf<TaskRoomModel>()

    fun setItems(mutableList: MutableList<TaskRoomModel>) {
        taskList.clear()
        taskList.addAll(mutableList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_table_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount() = taskList.count()

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) =
            holder.bind(taskList[position], listener)

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(taskModel: TaskRoomModel, listener: (TaskRoomModel) -> Unit) =
                with(itemView) {
                    if (taskModel.taskTitle.isNullOrEmpty()) {
                        tv_title.setGone()
                    } else {
                        tv_title.text = taskModel.taskTitle
                        tv_title.setVisible()
                    }
                    tv_description.text = taskModel.taskDescription
                    tv_status.text = taskModel.taskStatusEnum.name
                    tv_created_time.text = taskModel.taskCreatedTime.roomModelTimeToString()

                    setOnClickListener { listener(taskModel) }
                }
    }
}