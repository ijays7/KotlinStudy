package com.ijays.kotlinstudy.activity

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import com.ijays.kotlinstudy.BaseActivity
import com.ijays.kotlinstudy.R
import kotlinx.android.synthetic.main.activity_layout_animation.*

/**
 * Created by ijays on 2018/6/1.
 */
class LayoutAnimationActivity : BaseActivity() {

    private lateinit var animationController: LayoutAnimationController

    override fun getLayoutId(): Int {
        return R.layout.activity_layout_animation
    }

    override fun initView(savedInstanceState: Bundle?) {
        val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.item_anim_alpha)
        animation.fillAfter = true

        animationController = LayoutAnimationController(animation)
        animationController.order = LayoutAnimationController.ORDER_NORMAL

        container_view.clearAnimation()
        container_view.layoutAnimation = animationController

    }

    override fun onResume() {
        super.onResume()

        animationController.start()
    }


}