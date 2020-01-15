package com.ijays.kotlinstudy.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by ijays on 2019/2/25.
 */
class ViewPager2Adapter(fragmentActivity: FragmentActivity, private val dataList: List<Fragment>) :
        FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = dataList.size

    override fun createFragment(position: Int): Fragment {
        return dataList[position]
    }
}
