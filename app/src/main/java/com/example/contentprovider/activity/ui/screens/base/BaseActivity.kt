package com.example.contentprovider.activity.ui.screens.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<T : BaseContract.Presenter<V>, V : BaseContract.View>
    : AppCompatActivity(), BaseContract.View {

    protected lateinit var presenter: T
    protected abstract val view: V
    protected abstract fun createPresenter(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = lastCustomNonConfigurationInstance as? T ?: createPresenter()
        presenter.view = view
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
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