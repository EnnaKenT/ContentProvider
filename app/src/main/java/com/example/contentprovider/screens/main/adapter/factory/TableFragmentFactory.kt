package com.example.contentprovider.screens.main.adapter.factory

import androidx.fragment.app.Fragment
import com.example.contentprovider.screens.main.fragment.notes.NotesFragment
import com.example.contentprovider.screens.main.fragment.tasks.TasksFragment

object TableFragmentFactory {

    fun getFragment(type: TableEnumType): Fragment {
        return when (type) {
            TableEnumType.NOTE -> NotesFragment()
            TableEnumType.TASK -> TasksFragment()
        }
    }

    fun getTitle(type: TableEnumType): String {
        return type.name
    }
}
