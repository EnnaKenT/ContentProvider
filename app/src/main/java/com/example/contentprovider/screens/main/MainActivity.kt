package com.example.contentprovider.screens.main

import android.app.Activity
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.example.contentprovider.R
import com.example.contentprovider.screens.addTableItem.AddTableItemActivity
import com.example.contentprovider.screens.base.activity.BaseActivity
import com.example.contentprovider.screens.main.adapter.TableFragmentPagerAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainContract.Presenter, MainContract.View>(), MainContract.View,
    View.OnClickListener {
    override fun createPresenter(): MainContract.Presenter = MainPresenter()

    override val view: MainContract.View
        get() = this

    override fun getLayoutId() = R.layout.activity_main
    private lateinit var fragmentPagerAdapter: TableFragmentPagerAdapter

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
//                onPlusClicked()
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }
    }

    private fun onPlusClicked() {
        AddTableItemActivity.startActivity(
            this,
            fragmentPagerAdapter.getSelectedTableEnum(),
            ADD_TABLE_ITEM_REQUEST_CODE
        )
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ADD_TABLE_ITEM_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val noteModel = data?.getSerializableExtra(AddTableItemActivity.RETURNED_NOTE_MODEL_NAME)
            val taskModel = data?.getSerializableExtra(AddTableItemActivity.RETURNED_TASK_MODEL_NAME)

            if (noteModel != null) {

            }
            if (taskModel != null) {

            }
        }
        super.onActivityResult(requestCode, resultCode, data)
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
        snackbar.setActionTextColor(ContextCompat.getColor(this, R.color.colorPrimary))

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

    companion object {

        private const val ADD_TABLE_ITEM_REQUEST_CODE = 1000

    }

}
