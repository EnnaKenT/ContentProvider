package com.example.contentprovider.screens.main

import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.example.contentprovider.R
import com.example.contentprovider.screens.addNote.AddNoteActivity
import com.example.contentprovider.screens.addTask.AddTaskActivity
import com.example.contentprovider.screens.base.activity.BaseActivity
import com.example.contentprovider.screens.main.adapter.TableFragmentPagerAdapter
import com.example.contentprovider.screens.main.adapter.factory.TableTypeEnum
import com.google.android.material.snackbar.Snackbar
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
        menuInflater.inflate(R.menu.bottom_bar_menu, menu)
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
            TableTypeEnum.NOTE -> AddNoteActivity.startActivity(this)
            TableTypeEnum.TASK -> AddTaskActivity.startActivity(this)
        }
        overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        when (item.itemId) {
            R.id.action_search -> Toast.makeText(this, getString(R.string.action_search), Toast.LENGTH_SHORT).show()
        }

        return true
    }

    private fun displayMaterialSnackBar() {
        val marginSide = 0
        val marginBottom = 550
        val snackbar = Snackbar.make(coordinatorLayout, "FAB Clicked", Snackbar.LENGTH_LONG)
            .setAction("UNDO") { }
        // Changing message text color
        snackbar.setActionTextColor(ContextCompat.getColor(this, R.color.primaryColor))

        val snackbarView = snackbar.view
        val params = snackbarView.layoutParams as CoordinatorLayout.LayoutParams

        params.setMargins(
            params.leftMargin + marginSide,
            params.topMargin,
            params.rightMargin + marginSide,
            params.bottomMargin + marginBottom
        )

        snackbarView.layoutParams = params
        snackbar.show()
    }

}
