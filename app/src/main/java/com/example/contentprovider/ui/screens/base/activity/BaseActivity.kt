package com.example.contentprovider.ui.screens.base.activity

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.contentprovider.ui.screens.base.BaseContract

abstract class BaseActivity<T : BaseContract.BasePresenter<V>, V : BaseContract.BaseView> : AppCompatActivity(),
    BaseContract.BaseView {

    protected lateinit var presenter: T
    protected abstract val view: V
    protected abstract fun createPresenter(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        presenter = lastCustomNonConfigurationInstance as? T ?: createPresenter()
        presenter.view = view

        initData()
    }

    protected abstract fun initData()

    protected abstract fun getLayoutId(): Int

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroy() {
        presenter.view = null
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showError(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    override fun showError(@StringRes stringRes: Int) {
        Toast.makeText(this, getString(stringRes), Toast.LENGTH_LONG).show()
    }
}