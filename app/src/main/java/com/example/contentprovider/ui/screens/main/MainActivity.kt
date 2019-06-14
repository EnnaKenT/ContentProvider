package com.example.contentprovider.ui.screens.main

import android.app.ActivityOptions
import android.app.SearchManager
import android.content.Context
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import com.example.contentprovider.R
import com.example.contentprovider.ui.screens.addNote.AddNoteActivity
import com.example.contentprovider.ui.screens.addTask.AddTaskActivity
import com.example.contentprovider.ui.screens.base.activity.BaseActivity
import com.example.contentprovider.ui.screens.main.adapter.TableFragmentPagerAdapter
import com.example.contentprovider.ui.screens.main.adapter.TableTypeEnum
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainContract.Presenter, MainContract.View>(), MainContract.View,
    View.OnClickListener {

    private lateinit var fragmentPagerAdapter: TableFragmentPagerAdapter

    override fun createPresenter(): MainContract.Presenter = MainPresenter()
    override val view: MainContract.View = this
    override fun getLayoutId() = R.layout.activity_main

    override fun initData() {
        setSupportActionBar(bottomAppBar)

        initAdapter()
        initListener()
    }

    private fun initAdapter() {
        fragmentPagerAdapter = TableFragmentPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        viewPager.adapter = fragmentPagerAdapter
        val listener = fragmentPagerAdapter.CustomOnPageChangeListener()
        viewPager.addOnPageChangeListener(listener)
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun initListener() {
        fab?.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_bottom_bar, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        // Tells your app's SearchView to use this activity's searchable configuration
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.isIconifiedByDefault = false // Do not iconify the widget; expand it by default

        return true
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab -> {
                onPlusClicked()
            }
        }
    }

    private fun onPlusClicked() {
        val tableTypeEnum = fragmentPagerAdapter.getSelectedTableEnum()
        startAddItemActivity(tableTypeEnum)
    }

    private fun startAddItemActivity(tableTypeEnum: TableTypeEnum) {
        when (tableTypeEnum) {
            TableTypeEnum.NOTES -> startNoteActivity()
            TableTypeEnum.TASKS -> startTaskActivity()
        }
    }

    private fun startTaskActivity() {
        val intent = AddTaskActivity.getIntent(this)

        val transitionName = getString(R.string.db_item_transition_name)

        val transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this, bottomAppBar, transitionName)
        startActivity(intent, transitionActivityOptions.toBundle())

    }

    private fun startNoteActivity() {
        val intent = AddNoteActivity.getIntent(this)

        val transitionName = getString(R.string.db_item_transition_name)

        val transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this, bottomAppBar, transitionName)
        startActivity(intent, transitionActivityOptions.toBundle())
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        when (item.itemId) {
            R.id.action_search -> {
                super.onSearchRequested()
//                toast(R.string.action_search)
            }
        }

        return true
    }

}
