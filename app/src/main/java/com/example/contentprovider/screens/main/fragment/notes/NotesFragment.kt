package com.example.contentprovider.screens.main.fragment.notes

import androidx.recyclerview.widget.GridLayoutManager
import com.example.contentprovider.R
import com.example.contentprovider.room.notesTable.NoteRoomModel
import com.example.contentprovider.screens.base.fragment.BaseFragment
import com.example.contentprovider.screens.main.adapter.NotesTableAdapter
import com.example.contentprovider.screens.main.fragment.TableFragmentContract
import kotlinx.android.synthetic.main.fragment_table.*

class NotesFragment :
        BaseFragment<TableFragmentContract.Presenter<NoteRoomModel>, TableFragmentContract.View<NoteRoomModel>>(),
        TableFragmentContract.View<NoteRoomModel>, (NoteRoomModel) -> Unit {

    private lateinit var notesAdapter: NotesTableAdapter

    override val view = this
    override fun createPresenter() = NotesFragmentPresenter()
    override fun getLayoutId() = R.layout.fragment_table

    override fun initData() {
        initRv()
        presenter.prepareDatabaseModels()

    }

    private fun initRv() {
        notesAdapter = NotesTableAdapter(this)
        rv_table.setHasFixedSize(true)
        rv_table.layoutManager = GridLayoutManager(context, 2)
    }

    override fun invoke(noteModel: NoteRoomModel) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDatabaseModels(models: MutableList<NoteRoomModel>?) {
        if (models.isNullOrEmpty()) {
            //show placeholder
        } else {
            notesAdapter.setItems(models)
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