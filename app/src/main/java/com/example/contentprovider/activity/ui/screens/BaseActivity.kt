package com.example.contentprovider.activity.ui.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contentprovider.activity.ui.BasePresenter
import com.example.contentprovider.activity.ui.BaseView

abstract class BaseActivity<T : BasePresenter<V>, in V : BaseView> : AppCompatActivity(),
    BaseView {

    lateinit var presenter: T
    abstract fun providePresenter(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = providePresenter()
    }

}