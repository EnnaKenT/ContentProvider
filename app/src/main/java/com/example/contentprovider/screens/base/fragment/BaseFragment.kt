package com.example.contentprovider.screens.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.contentprovider.screens.base.BaseContract

abstract class BaseFragment<T : BaseContract.Presenter<V>, V : BaseContract.View> : Fragment(), BaseContract.View {

    protected lateinit var presenter: T
    protected abstract val view: V
    protected abstract fun createPresenter(): T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = createPresenter()
        presenter.view = view
        return inflater.inflate(getLayoutId(), container, false)
    }

    protected abstract fun getLayoutId(): Int

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
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(textRes: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}