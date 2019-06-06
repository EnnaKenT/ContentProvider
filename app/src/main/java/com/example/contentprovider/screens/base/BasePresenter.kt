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
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResume() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPause() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStop() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        job.cancel()
    }

}