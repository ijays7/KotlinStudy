package com.ijays.kotlinstudy.activity

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ijays.kotlinstudy.BaseActivity
import com.ijays.kotlinstudy.R
import com.ijays.kotlinstudy.adapter.ViewPager2Adapter
import com.ijays.kotlinstudy.fragment.NaviFirstFragment
import com.ijays.kotlinstudy.fragment.NaviSecondFragment
import com.ijays.kotlinstudy.fragment.NaviThirdFragment
import kotlinx.android.synthetic.main.activity_view_pager2_layout.*

/**
 * Created by ijays on 2019/2/25.
 */
class ViewPager2Activity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_view_pager2_layout

    override fun initData() {
        val fragmentList = listOf(NaviFirstFragment.newInstance(1),
                NaviSecondFragment.newInstance(1),
                NaviThirdFragment.newInstance(1))
        val adapter = ViewPager2Adapter(this@ViewPager2Activity, dataList = fragmentList)

        view_pager2.adapter = adapter

        view_pager2.orientation = ViewPager2.ORIENTATION_VERTICAL

        view_pager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)

                Log.e("ijays", "OnPageScrollStateChanged")
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                Log.e("ijays", "onPageSelected====>$position")
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                Log.e("ijays", "onPageScrolled")
            }

        })
    }


}
