package com.example.contentprovider.screens.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.example.contentprovider.R
import com.example.contentprovider.screens.base.activity.BaseActivity
import com.example.contentprovider.screens.main.adapter.NoteTaskAdapter
import com.example.contentprovider.screens.main.fragment.NotesFragment
import com.example.contentprovider.screens.main.fragment.TasksFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainContract.Presenter, MainContract.View>(), MainContract.View {

    override fun createPresenter(): MainContract.Presenter = MainPresenter()
    override val view: MainContract.View
        get() = this

    /**
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * androidx.fragment.app.FragmentStatePagerAdapter.
     */
    private lateinit var sectionsPagerAdapter: NoteTaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(bottomAppBar)

        initAdapter()

        fab?.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun getLayoutId() = R.layout.activity_main

    private fun initAdapter() {
        sectionsPagerAdapter = NoteTaskAdapter(supportFragmentManager)
        sectionsPagerAdapter.addFragment(NotesFragment(), getString(R.string.note_frag_title))
        sectionsPagerAdapter.addFragment(TasksFragment(), getString(R.string.task_frag_title))

        // Set up the ViewPager with the sections adapter.
        viewPager.adapter = sectionsPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
        presenter.onPause()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.bottom_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        when (id) {
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

}
