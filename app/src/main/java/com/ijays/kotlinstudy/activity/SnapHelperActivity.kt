package com.ijays.kotlinstudy.activity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.ijays.kotlinstudy.BaseActivity
import com.ijays.kotlinstudy.R
import com.ijays.kotlinstudy.adapter.SnapHelperAdapter
import com.ijays.kotlinstudy.util.helper.MySnapHelper
import kotlinx.android.synthetic.main.activity_snap_helper.*

/**
 * Created by ijays on 2019/4/2.
 */
class SnapHelperActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_snap_helper

    override fun initData() {
        val adapter = SnapHelperAdapter(generateData())
        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,
                false)
        recycler_view.adapter = adapter
        recycler_view.setHasFixedSize(true)

//        LinearSnapHelper().attachToRecyclerView(recycler_view)
//        PagerSnapHelper().attachToRecyclerView(recycler_view)
        MySnapHelper().attachToRecyclerView(recycler_view)

    }

    private fun generateData(): MutableList<String> {
        val list = arrayListOf<String>()
        for (i in 0 until 30) {
            list.add(i.toString())
        }

        return list
    }
}