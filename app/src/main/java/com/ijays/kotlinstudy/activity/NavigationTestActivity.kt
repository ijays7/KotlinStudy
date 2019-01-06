package com.ijays.kotlinstudy.activity

import androidx.navigation.Navigation.findNavController
import com.ijays.kotlinstudy.BaseActivity
import com.ijays.kotlinstudy.R

/**
 * Created by ijays on 2019/1/2.
 */
class NavigationTestActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_navigation_test_layout


    override fun onSupportNavigateUp(): Boolean {
        return findNavController(this, R.id.navi_host).navigateUp()
    }
}