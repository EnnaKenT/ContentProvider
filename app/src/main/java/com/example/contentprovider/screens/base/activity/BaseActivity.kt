package com.example.contentprovider.screens.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contentprovider.screens.base.BaseContract

abstract class BaseActivity<T : BaseContract.Presenter<V>, V : BaseContract.View> : AppCompatActivity(),
    BaseContract.View {

    protected lateinit var presenter: T
    protected abstract val view: V
    protected abstract fun createPresenter(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        presenter = lastCustomNonConfigurationInstance as? T ?: createPresenter()
        presenter.view = view
    }

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
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(textRes: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}