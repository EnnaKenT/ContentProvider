package com.example.contentprovider.ui.screens.main.fragment.notes

import android.app.ActivityOptions
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.contentprovider.R
import com.example.contentprovider.room.notesTable.NoteRoomModel
import com.example.contentprovider.ui.screens.addNote.NoteDetailsActivity
import com.example.contentprovider.ui.screens.base.fragment.BaseFragment
import com.example.contentprovider.ui.screens.main.adapter.notes.NotesTableAdapter
import com.example.contentprovider.ui.screens.main.fragment.TableFragmentContract
import com.example.contentprovider.utils.setGone
import com.example.contentprovider.utils.setVisible
import kotlinx.android.synthetic.main.fragment_table.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class NotesFragment :
        BaseFragment<TableFragmentContract.Presenter<NoteRoomModel>, TableFragmentContract.View<NoteRoomModel>>(),
        TableFragmentContract.View<NoteRoomModel>, (NoteRoomModel, View) -> Unit {

    private lateinit var notesAdapter: NotesTableAdapter

    override val view = this
    override fun createPresenter() = NotesFragmentPresenter()
    override fun getLayoutId() = R.layout.fragment_table

    override fun initData() {
        initRv()
        presenter.prepareDatabaseModels()
//        consumeChannel()
    }

//    private fun consumeChannel() {
//        val channel = (activity?.application as App).broadcastChannel
//        GlobalScope.launch {
//            channel.consumeEach { value ->
//                if (value is ChannelEvents.DatabaseItemUpdated) {
//                    Log.i("duck", "received")
//                }
//
//            }
//        }
//    }

    private fun initRv() {
        notesAdapter = NotesTableAdapter(this)
        rv_table.adapter = notesAdapter
        rv_table.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rv_table.setHasFixedSize(true)
    }

    override fun invoke(noteModel: NoteRoomModel, view: View) {
        activity?.let {
            val transitionName = getString(R.string.db_item_transition_name)

            val transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(it, view, transitionName)
            val intent = NoteDetailsActivity.getIntent(it, noteModel)
            startActivity(intent, transitionActivityOptions.toBundle())
        }
    }

    override fun showDatabaseModels(models: MutableList<NoteRoomModel>?) {
        if (models.isNullOrEmpty()) {
            tv_no_items?.setVisible()
            rv_table?.setGone()
        } else {
            notesAdapter.setItems(models)
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