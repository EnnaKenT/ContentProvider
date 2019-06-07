package com.example.contentprovider.screens.addTableItem

import android.app.Activity
import android.content.Intent
import com.example.contentprovider.R
import com.example.contentprovider.screens.base.activity.BaseActivity
import com.example.contentprovider.screens.main.adapter.factory.TableEnumType

class AddTableItemActivity : BaseActivity<AddTableItemContract.Presenter, AddTableItemContract.View>(),
    AddTableItemContract.View {

    private lateinit var tableEnumType: TableEnumType

    override val view = this
    override fun createPresenter() = AddTableItemPresenter()
    override fun getLayoutId() = R.layout.activity_add_table_item

    override fun initData() {
        tableEnumType = intent.getSerializableExtra(ARG_TABLE_TYPE_ENUM) as TableEnumType
    }

    companion object {

        private const val ARG_TABLE_TYPE_ENUM = "table_type_enum"
        const val RETURNED_NOTE_MODEL_NAME = "note_model_name"
        const val RETURNED_TASK_MODEL_NAME = "task_model_name"

        fun startActivity(
            activity: Activity,
            tableEnumType: TableEnumType,
            startActivityRequestCode: Int
        ) {
            val intent = Intent(activity, AddTableItemActivity::class.java)
            intent.putExtra(ARG_TABLE_TYPE_ENUM, tableEnumType)
            activity.startActivityForResult(intent, startActivityRequestCode)
        }

    }

}