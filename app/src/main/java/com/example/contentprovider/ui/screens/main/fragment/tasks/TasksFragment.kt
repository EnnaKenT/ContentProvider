package com.example.contentprovider.ui.screens.main.fragment.tasks

import android.app.ActivityOptions
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.contentprovider.R
import com.example.contentprovider.room.tasksTable.TaskRoomModel
import com.example.contentprovider.ui.screens.addTask.AddTaskActivity
import com.example.contentprovider.ui.screens.base.fragment.BaseFragment
import com.example.contentprovider.ui.screens.main.adapter.tasks.TasksTableAdapter
import com.example.contentprovider.ui.screens.main.fragment.TableFragmentContract
import com.example.contentprovider.ui.screens.main.fragment.notes.NotesFragment
import com.example.contentprovider.utils.setGone
import com.example.contentprovider.utils.setVisible
import kotlinx.android.synthetic.main.fragment_table.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class TasksFragment :
        BaseFragment<TableFragmentContract.Presenter<TaskRoomModel>, TableFragmentContract.View<TaskRoomModel>>(),
        TableFragmentContract.View<TaskRoomModel>, (TaskRoomModel, View) -> Unit {

    private lateinit var tasksAdapter: TasksTableAdapter

    override val view = this
    override fun createPresenter() = TasksFragmentPresenter()
    override fun getLayoutId() = R.layout.fragment_table

    override fun initData() {
        initRv()
        presenter.prepareDatabaseModels()
    }

    override fun onStart() {
        super.onStart()
//        sendChannel()
    }

//    private fun sendChannel() {
//        val channel = (activity?.application as App).broadcastChannel
//        GlobalScope.launch {
//            channel.send(ChannelEvents.DatabaseItemUpdated())
//            channel.send(ChannelEvents.DatabaseItemUpdated())
//            channel.send(ChannelEvents.DatabaseItemUpdated())
//            channel.send(ChannelEvents.DatabaseItemUpdated())
//            channel.send(ChannelEvents.DatabaseItemUpdated())
//        }
//    }

    private fun initRv() {
        tasksAdapter = TasksTableAdapter(this)
        rv_table.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rv_table.adapter = tasksAdapter
        rv_table.setHasFixedSize(true)
    }

    override fun invoke(taskModel: TaskRoomModel, view: View) {
        activity?.let {
            val transitionName = getString(R.string.db_item_transition_name)

            val transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(it, view, transitionName)
            val intent = AddTaskActivity.getIntent(it, taskModel)
            startActivity(intent, transitionActivityOptions.toBundle())
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