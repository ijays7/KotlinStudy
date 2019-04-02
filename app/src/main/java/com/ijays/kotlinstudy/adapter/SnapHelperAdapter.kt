package com.ijays.kotlinstudy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ijays.kotlinstudy.R
import kotlinx.android.synthetic.main.item_snap_helper_layout.view.*

/**
 * Created by ijays on 2019/4/2.
 */
class SnapHelperAdapter(private var dataList: MutableList<String>) : RecyclerView.Adapter<SnapHelperAdapter.SnapHelperVH>() {

    private var imgList = arrayListOf(R.drawable.ccdzz, R.drawable.dfh, R.drawable.dlzs,
            R.drawable.jdzz, R.drawable.sgkptt, R.drawable.ttxss, R.drawable.zmq, R.drawable.zzhx)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnapHelperVH {
        return SnapHelperVH(LayoutInflater.from(parent.context).inflate(R.layout.item_snap_helper_layout,
                parent, false))
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: SnapHelperVH, position: Int) {
        holder.showData(dataList[position], position)
    }

    inner class SnapHelperVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun showData(s: String, position: Int) {
            itemView.iv_cover.setImageResource(imgList[position % 8])

            itemView.tv_title.text = position.toString()

        }

    }
}