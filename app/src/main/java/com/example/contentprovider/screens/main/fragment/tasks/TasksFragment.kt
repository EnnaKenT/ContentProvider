package com.example.contentprovider.screens.main.fragment.tasks

import androidx.recyclerview.widget.GridLayoutManager
import com.example.contentprovider.R
import com.example.contentprovider.room.tasksTable.TaskRoomModel
import com.example.contentprovider.screens.addTask.AddTaskActivity
import com.example.contentprovider.screens.base.fragment.BaseFragment
import com.example.contentprovider.screens.main.adapter.TasksTableAdapter
import com.example.contentprovider.screens.main.fragment.TableFragmentContract
import com.example.contentprovider.screens.main.fragment.notes.NotesFragment
import com.example.contentprovider.utils.setGone
import com.example.contentprovider.utils.setVisible
import kotlinx.android.synthetic.main.fragment_table.*

class TasksFragment :
    BaseFragment<TableFragmentContract.Presenter<TaskRoomModel>, TableFragmentContract.View<TaskRoomModel>>(),
    TableFragmentContract.View<TaskRoomModel>, (TaskRoomModel) -> Unit {

    private lateinit var tasksAdapter: TasksTableAdapter

    override val view = this
    override fun createPresenter() = TasksFragmentPresenter()
    override fun getLayoutId() = R.layout.fragment_table

    override fun initData() {
        initRv()
        presenter.prepareDatabaseModels()

    }

    private fun initRv() {
        tasksAdapter = TasksTableAdapter(this)
        rv_table.setHasFixedSize(true)
        rv_table.layoutManager = GridLayoutManager(context, 2)
    }

    override fun invoke(taskModel: TaskRoomModel) {
        activity?.let {
            AddTaskActivity.startActivity(it, taskModel)
            it.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }
    }

    override fun showDatabaseModels(models: MutableList<TaskRoomModel>?) {
        if (models.isNullOrEmpty()) {
            tv_no_items?.setVisible()
            rv_table?.setGone()
        } else {
            tasksAdapter.setItems(models)
            tv_no_items?.setGone()
            rv_table?.setVisible()
        }
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        fun newInstance(): NotesFragment {
            val fragment = NotesFragment()
//            val args = Bundle()
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
//            fragment.arguments = args
            return fragment
        }
    }

}