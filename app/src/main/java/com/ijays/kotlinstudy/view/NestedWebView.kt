package com.ijays.kotlinstudy.view

import android.content.Context
import android.support.v4.view.MotionEventCompat
import android.support.v4.view.NestedScrollingChild
import android.support.v4.view.NestedScrollingChildHelper
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.webkit.WebView
import java.util.jar.Attributes

/**
 * Created by ijays on 2018/6/25.
 */
class NestedWebView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int = 0)
    : WebView(context, attributeSet, defStyle),
        NestedScrollingChild {

    private var lastMotionY = 0

    private var nestedYOffset: Int = 0

    private val scrollOffset: IntArray = IntArray(2)

    private val scrollConsumed: IntArray = IntArray(2)

    private var childHelper: NestedScrollingChildHelper



    // 如果使用@JvmOverloads，则自动重载了构造函数；如果不使用@JvmOverloads，则需要声明多个次构造函数，次构造函数需要调用主构造函数
//    constructor(context: Context) : this(context, null, 0)

//    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    init {
        childHelper = NestedScrollingChildHelper(this)
        setNestedScrollingEnabled(true)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var result = false


        val trackedEvent = MotionEvent.obtain(event)

        val action = MotionEventCompat.getActionMasked(event)
        if (action == MotionEvent.ACTION_DOWN) {
            nestedYOffset = 0
        }

        val y = event?.y?.toInt() ?: 0

        when (action) {
            MotionEvent.ACTION_DOWN -> {
                lastMotionY = y
                startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL)
                result = super.onTouchEvent(event)

            }
            MotionEvent.ACTION_MOVE -> {
                var deltaY = lastMotionY - y

                if (dispatchNestedPreScroll(0, deltaY, scrollConsumed, scrollOffset)) {
                    deltaY -= scrollConsumed[1]
                    trackedEvent.offsetLocation(0.toFloat(), scrollOffset[1].toFloat())
                    nestedYOffset += scrollOffset[1]
                }

                var oldY = scrollY
                lastMotionY = y - scrollOffset[1]
                var newScrollY = Math.max(0, oldY + deltaY)
                deltaY -= newScrollY - oldY

                if (dispatchNestedScroll(0, newScrollY - deltaY, 0, deltaY, scrollOffset)) {
                    lastMotionY -= scrollOffset[1]
                    trackedEvent.offsetLocation(0.toFloat(), scrollOffset[1].toFloat())
                    nestedYOffset += scrollOffset[1]
                }

                if (scrollConsumed[1] == 0 && scrollOffset[1] == 0) {
                    trackedEvent.recycle()
                    result = super.onTouchEvent(trackedEvent)
                }


            }
            MotionEvent.ACTION_POINTER_DOWN,
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {
                stopNestedScroll()
                result = super.onTouchEvent(event)
            }
        }


        return result
    }

    // NestedScrollingChild

    override fun setNestedScrollingEnabled(enabled: Boolean) {
        childHelper.isNestedScrollingEnabled = true
    }

    override fun isNestedScrollingEnabled(): Boolean {
        return childHelper.isNestedScrollingEnabled
    }

    override fun startNestedScroll(axes: Int): Boolean {
        return childHelper.startNestedScroll(axes)
    }

    override fun stopNestedScroll() {
        childHelper.stopNestedScroll()
    }

    override fun hasNestedScrollingParent(): Boolean {
        return childHelper.hasNestedScrollingParent()
    }

    override fun dispatchNestedPreScroll(dx: Int, dy: Int, consumed: IntArray?, offsetInWindow: IntArray?): Boolean {
        return childHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow)
    }

    override fun dispatchNestedScroll(dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, offsetInWindow: IntArray?): Boolean {
        return childHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow)
    }

    override fun dispatchNestedPreFling(velocityX: Float, velocityY: Float): Boolean {
        return childHelper.dispatchNestedPreFling(velocityX, velocityY)
    }

    override fun dispatchNestedFling(velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        return childHelper.dispatchNestedFling(velocityX, velocityY, consumed)
    }


}