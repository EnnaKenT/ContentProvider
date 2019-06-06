package com.example.contentprovider.screens.main.fragment

import android.os.Bundle
import android.view.View
import com.example.contentprovider.R
import com.example.contentprovider.screens.base.fragment.BaseFragment

class NotesFragment :
    BaseFragment<TableFragmentContract.Presenter, TableFragmentContract.View>(), TableFragmentContract.View {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        section_label.text = getString(R.string.section_format, arguments?.getInt(ARG_SECTION_NUMBER))
    }

    override val view = this

    override fun createPresenter() = TableFragmentPresenter()

    override fun getLayoutId() = R.layout.fragment_table

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
        fun newInstance(sectionNumber: Int): NotesFragment {
            val fragment = NotesFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }

}