package com.example.contentprovider.screens.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V> {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val job = SupervisorJob()

    override var view: V? = null

    override fun onStart() {
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {
        job.cancel()
    }

}