package com.example.contentprovider.screens.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.contentprovider.screens.base.BaseContract
import com.example.contentprovider.screens.base.activity.BaseActivity

abstract class BaseFragment<T : BaseContract.BasePresenter<V>, V : BaseContract.BaseView> : Fragment(), BaseContract.BaseView {

    protected lateinit var presenter: T
    protected abstract val view: V
    protected abstract fun createPresenter(): T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = createPresenter()
        presenter.view = view
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initData()

    override fun onStart() {
        presenter.onStart()
        super.onStart()
    }

    override fun onResume() {
        presenter.onResume()
        super.onResume()
    }

    override fun onPause() {
        presenter.onPause()
        super.onPause()
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        presenter.view = null
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showError(text: String) {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).showError(text)
        }
    }

    override fun showError(@StringRes stringRes: Int) {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).showError(stringRes)
        }
    }

}