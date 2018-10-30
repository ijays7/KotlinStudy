package com.ijays.kotlinstudy.view.behavior

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout

/**
 * Created by ijays on 2018/7/6.
 */
class TitleBarBehavior @JvmOverloads constructor(context: Context, attributeSet: AttributeSet?)
    : CoordinatorLayout.Behavior<View>(context, attributeSet) {

    private var totalRange: Float = 0f

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        return dependency is androidx.recyclerview.widget.RecyclerView
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {

        if (totalRange == 0f) {
            totalRange = dependency?.top?.toFloat() ?: 0f
        }

        val rate = 1 - (dependency?.top?.div(totalRange) ?: 0f)

        Log.e("SONGJIE", "child height----->${child?.height}----->${dependency?.top}")

        child?.alpha = rate

        return true
    }


}