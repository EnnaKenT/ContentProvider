package com.example.contentprovider.activity.ui.screens.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<T : BaseContract.Presenter<V>, in V : BaseContract.View>
    : AppCompatActivity(), BaseContract.View {

    protected var mPresenter: T? = null
    abstract fun providePresenter(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = providePresenter()
    }

    override fun onStart() {
        super.onStart()
        mPresenter?.bindView(this@BaseActivity)
    }

    override fun onStop() {
        super.onStop()
        mPresenter?.unbindView()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.unbindView()
    }
}