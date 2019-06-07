package com.example.contentprovider.screens.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.contentprovider.screens.main.adapter.factory.TableEnumType
import com.example.contentprovider.screens.main.adapter.factory.TableFragmentFactory


class TableFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    var selectedFragmentPosition: Int = 0

    override fun getPageTitle(position: Int): CharSequence? {
        return TableFragmentFactory.getTitle(TableEnumType.values()[position])
    }

    override fun getItem(position: Int): Fragment {
        return TableFragmentFactory.getFragment(TableEnumType.values()[position])
    }

    override fun getCount(): Int {
        return TableEnumType.values().size
    }

    fun getSelectedTableEnum(): TableEnumType {
        return TableEnumType.values().first { it.ordinal == selectedFragmentPosition }
    }

    inner class CustomOnPageChangeListener : ViewPager.SimpleOnPageChangeListener() {
        override fun onPageSelected(position: Int) {
            selectedFragmentPosition = position
            super.onPageSelected(position)
        }
    }
}