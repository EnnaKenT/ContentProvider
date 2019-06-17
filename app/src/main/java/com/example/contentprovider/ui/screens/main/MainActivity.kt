package com.example.contentprovider.ui.screens.main

import android.app.ActivityOptions
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import com.example.contentprovider.R
import com.example.contentprovider.ui.screens.noteDetails.NoteDetailsActivity
import com.example.contentprovider.ui.screens.taskDetails.TaskDetailsActivity
import com.example.contentprovider.ui.screens.base.activity.BaseActivity
import com.example.contentprovider.ui.screens.main.adapter.TableFragmentPagerAdapter
import com.example.contentprovider.ui.screens.main.adapter.TableTypeEnum
import com.example.contentprovider.utils.hideKeyboard
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

        menu.findItem(R.id.action_search).run {
            setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                    bottomAppBar.cradleVerticalOffset = resources.getDimension(R.dimen.fabCradleVerticalOffsetSearch)
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                    hideKeyboard()
                    bottomAppBar.cradleVerticalOffset = 0f
                    return true
                }
            })
            (actionView as SearchView).run {
                val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
                // Tells your app's SearchView to use this activity's searchable configuration
                setSearchableInfo(searchManager.getSearchableInfo(componentName))
                isIconifiedByDefault = false // Do not iconify the widget; expand it by default
            }
        }


        return true
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab -> onPlusClicked()
        }
    }

    private fun onPlusClicked() {
        val tableTypeEnum = fragmentPagerAdapter.getSelectedTableEnum()
        startAddItemActivity(tableTypeEnum)
    }

    private fun startAddItemActivity(tableTypeEnum: TableTypeEnum) {
        when (tableTypeEnum) {
            TableTypeEnum.NOTE -> startNoteActivity()
            TableTypeEnum.TASK -> startTaskActivity()
        }
    }

    private fun startTaskActivity() {
        val intent = TaskDetailsActivity.getIntent(this)

        val transitionName = getString(R.string.db_item_transition_name)

        val transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this, bottomAppBar, transitionName)
        startActivity(intent, transitionActivityOptions.toBundle())

    }

    private fun startNoteActivity() {
        val intent = NoteDetailsActivity.getIntent(this)

        val transitionName = getString(R.string.db_item_transition_name)

        val transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this, bottomAppBar, transitionName)
        startActivity(intent, transitionActivityOptions.toBundle())
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        when (intent?.action) {
            Intent.ACTION_SEARCH -> parseSearchIntent(intent)
        }
    }

    private fun parseSearchIntent(intent: Intent?) {
        val tableId = intent?.getStringExtra(SearchManager.QUERY)
        val tableTypeEnum = TableTypeEnum.values().firstOrNull { it.name == intent?.dataString }

        when (tableTypeEnum) {
            TableTypeEnum.NOTE -> NoteDetailsActivity.startActivity(this, noteId = tableId)
            TableTypeEnum.TASK -> TaskDetailsActivity.startActivity(this, taskId = tableId)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        when (item.itemId) {
            R.id.action_search -> {
//                toast(R.string.action_search)
            }
        }

        return true
    }

}
