package com.ijays.kotlinstudy.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by ijays on 2019/2/25.
 */
class ViewPager2Adapter(fragmentManager: FragmentManager, private val dataList: List<Fragment>) :
        FragmentStateAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment {
        return dataList[position]
    }

    override fun getItemCount() = dataList.size
}
