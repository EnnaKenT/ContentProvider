package com.example.contentprovider.ui.screens.base.activity

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.contentprovider.ui.screens.base.BaseContract
import com.example.contentprovider.utils.toast

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
        toast(text)
    }

    override fun showError(@StringRes stringRes: Int) {
        toast(stringRes)
    }
}