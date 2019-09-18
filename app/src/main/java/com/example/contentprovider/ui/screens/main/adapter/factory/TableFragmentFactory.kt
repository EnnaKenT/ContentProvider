package com.example.contentprovider.ui.screens.main.adapter.factory

import androidx.fragment.app.Fragment
import com.example.contentprovider.ui.screens.main.adapter.TableTypeEnum
import com.example.contentprovider.ui.screens.main.fragment.notes.NotesFragment
import com.example.contentprovider.ui.screens.main.fragment.tasks.TasksFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi

object TableFragmentFactory {

    @ExperimentalCoroutinesApi
    fun getFragment(type: TableTypeEnum): Fragment {
        return when (type) {
            TableTypeEnum.NOTE -> NotesFragment()
            TableTypeEnum.TASK -> TasksFragment()
        }
    }

    fun getTitle(type: TableTypeEnum): String {
        return type.name
    }
}
