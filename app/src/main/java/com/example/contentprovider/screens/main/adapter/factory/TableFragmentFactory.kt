package com.example.contentprovider.screens.main.adapter.factory

import androidx.fragment.app.Fragment
import com.example.contentprovider.screens.main.adapter.TableTypeEnum
import com.example.contentprovider.screens.main.fragment.notes.NotesFragment
import com.example.contentprovider.screens.main.fragment.tasks.TasksFragment

object TableFragmentFactory {

    fun getFragment(type: TableTypeEnum): Fragment {
        return when (type) {
            TableTypeEnum.NOTES -> NotesFragment()
            TableTypeEnum.TASKS -> TasksFragment()
        }
    }

    fun getTitle(type: TableTypeEnum): String {
        return type.name
    }
}
