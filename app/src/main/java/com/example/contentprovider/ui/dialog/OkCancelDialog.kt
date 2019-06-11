package com.example.contentprovider.ui.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.contentprovider.R
import com.example.contentprovider.utils.setVisible
import kotlinx.android.synthetic.main.dialog_ok_cancel.*

class OkCancelDialog : DialogFragment() {

    private var leftBtnClickListener: OnLeftBtnClickListener? = null

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_ok_cancel, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.viewTreeObserver.addOnGlobalLayoutListener {
            dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT)
        }

        setData()
    }

    private fun setData() {
        btn_left_dialog?.setOnClickListener { leftBtnClickListener?.btnClicked() }
        btn_right_dialog?.setOnClickListener { dismiss() }

        arguments?.getString(ARG_TITLE)?.let {
            tv_title_dialog?.text = it
            tv_title_dialog?.setVisible()
        }
        arguments?.getString(ARG_MESSAGE)?.let {
            tv_message_dialog?.text = it
            tv_message_dialog?.setVisible()
        }
    }

    fun setLeftBtnListener(listener: OnLeftBtnClickListener) {
        leftBtnClickListener = listener
    }

    override fun dismiss() {
        super.dismissAllowingStateLoss()
    }

    fun show(manager: FragmentManager) {
        super.show(manager, javaClass.simpleName)
    }

    interface OnLeftBtnClickListener {
        fun btnClicked()
    }

    companion object {

        private const val ARG_TITLE: String = "title"
        private const val ARG_MESSAGE: String = "message"

        fun newInstance(title: String, message: String): OkCancelDialog {
            val dialog = OkCancelDialog()
            val bundle = Bundle()
            bundle.putString(ARG_TITLE, title)
            bundle.putString(ARG_MESSAGE, message)
            dialog.arguments = bundle
            return dialog
        }
    }

}
