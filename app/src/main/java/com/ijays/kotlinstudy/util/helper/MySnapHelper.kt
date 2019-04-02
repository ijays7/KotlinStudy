package com.ijays.kotlinstudy.util.helper

import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.*


/**
 * snapHelper 是一个抽象类，使用 snapHelper 需要实现三个方法
 * Created by ijays on 2019/4/2.
 */
class MySnapHelper : SnapHelper() {
    companion object {

        private const val INVALID_DISTANCE = 0f
        private const val MILLISECONDS_PER_INCH = 50f
    }

    private var recyclerView: RecyclerView? = null
    private var horizontalHelper: OrientationHelper? = null

    override fun attachToRecyclerView(recyclerView: RecyclerView?) {
        this.recyclerView = recyclerView
        super.attachToRecyclerView(recyclerView)
    }

    override fun calculateDistanceToFinalSnap(layoutManager: RecyclerView.LayoutManager, targetView: View): IntArray? {
        val out = intArrayOf(0, 0)
        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToStart(targetView, getHorizontalHelper(layoutManager))
        } else {
            out[0] = 0
        }
        if (layoutManager.canScrollVertically()) {

        } else {

        }
        return out
    }

    private fun distanceToStart(targetView: View, horizontalHelper: OrientationHelper?): Int {
        return horizontalHelper?.getDecoratedStart(targetView)?.minus(horizontalHelper.startAfterPadding)
                ?: 0
    }

    override fun findTargetSnapPosition(layoutManager: RecyclerView.LayoutManager, velocityX: Int, velocityY: Int): Int {
        if (layoutManager !is RecyclerView.SmoothScroller.ScrollVectorProvider) {
            return RecyclerView.NO_POSITION
        }

        val itemCount = layoutManager.itemCount
        if (itemCount == 0) {
            return RecyclerView.NO_POSITION
        }
        val currentView = findSnapView(layoutManager = layoutManager)
                ?: return RecyclerView.NO_POSITION

        val currentPosition = layoutManager.getPosition(currentView)
        if (currentPosition == RecyclerView.NO_POSITION) {
            return RecyclerView.NO_POSITION
        }

        // deltaJumps sign comes from the velocity which may not match the order of children in
        // the LayoutManager. To overcome this, we ask for a vector from the LayoutManager to
        // get the direction.
        val vectorForEnd = layoutManager.computeScrollVectorForPosition(itemCount - 1)
                ?: return RecyclerView.NO_POSITION

        // 在松手之后,列表最多只能滚多一屏的item数
        val deltaThreshold = layoutManager.width / getHorizontalHelper(layoutManager)!!.getDecoratedMeasurement(currentView)

        var hDeltaJump: Int
        if (layoutManager.canScrollHorizontally()) {
            hDeltaJump = estimateNextPositionDiffForFling(layoutManager,
                    getHorizontalHelper(layoutManager)!!, velocityX, 0)

            if (hDeltaJump > deltaThreshold) {
                hDeltaJump = deltaThreshold
            }
            if (hDeltaJump < -deltaThreshold) {
                hDeltaJump = -deltaThreshold
            }

            if (vectorForEnd.x < 0) {
                hDeltaJump = -hDeltaJump
            }
        } else {
            hDeltaJump = 0
        }

        if (hDeltaJump == 0) {
            return RecyclerView.NO_POSITION
        }

        var targetPos = currentPosition + hDeltaJump
        if (targetPos < 0) {
            targetPos = 0
        }
        if (targetPos >= itemCount) {
            targetPos = itemCount - 1
        }
        return targetPos
    }

    private fun estimateNextPositionDiffForFling(layoutManager: RecyclerView.LayoutManager,
                                                 helper: OrientationHelper, velocityX: Int, velocityY: Int): Int {
        val distances = calculateScrollDistance(velocityX, velocityY)
        val distancePerChild = computeDistancePerChild(layoutManager, helper)
        if (distancePerChild <= 0) {
            return 0
        }
        val distance = distances[0]
        return if (distance > 0) {
            Math.floor((distance / distancePerChild).toDouble()).toInt()
        } else {
            Math.ceil((distance / distancePerChild).toDouble()).toInt()
        }
    }

    private fun computeDistancePerChild(layoutManager: RecyclerView.LayoutManager,
                                        helper: OrientationHelper): Float {
        var minPosView: View? = null
        var maxPosView: View? = null
        var minPos = Integer.MAX_VALUE
        var maxPos = Integer.MIN_VALUE
        val childCount = layoutManager.childCount
        if (childCount == 0) {
            return INVALID_DISTANCE
        }

        for (i in 0 until childCount) {
            val child = layoutManager.getChildAt(i)
            val pos = layoutManager.getPosition(child!!)
            if (pos == RecyclerView.NO_POSITION) {
                continue
            }
            if (pos < minPos) {
                minPos = pos
                minPosView = child
            }
            if (pos > maxPos) {
                maxPos = pos
                maxPosView = child
            }
        }
        if (minPosView == null || maxPosView == null) {
            return INVALID_DISTANCE
        }
        val start = Math.min(helper.getDecoratedStart(minPosView),
                helper.getDecoratedStart(maxPosView))
        val end = Math.max(helper.getDecoratedEnd(minPosView),
                helper.getDecoratedEnd(maxPosView))
        val distance = end - start
        return if (distance == 0) {
            INVALID_DISTANCE
        } else 1f * distance / (maxPos - minPos + 1)
    }


    override fun findSnapView(layoutManager: RecyclerView.LayoutManager): View? {
        return findStartView(layoutManager, getHorizontalHelper(layoutManager))
    }

    private fun findStartView(layoutManager: RecyclerView.LayoutManager, helper: OrientationHelper?): View? {
        if (layoutManager is LinearLayoutManager) {
            val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
            if (firstVisibleItem == RecyclerView.NO_POSITION) {
                return null
            }

            // 如果已经到最后一个元素，则不做操作，否则最后一个元素可能始终无法完全显示
            if (layoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.itemCount - 1) {
                return null
            }

            val firstChildView = layoutManager.findViewByPosition(firstVisibleItem)

            return if ((helper?.getDecoratedEnd(firstChildView)
                            ?: 0) >= helper?.getDecoratedMeasurement(firstChildView)?.div(2) ?: 0
                    && helper?.getDecoratedEnd(firstChildView) ?: 0 >= 0) {
                // 这里如果第一个元素可见的范围大于了它自身宽度的一般，则显示该元素，否则显示下一个元素
                firstChildView
            } else {
                layoutManager.findViewByPosition(firstVisibleItem + 1)
            }

        }

        return null
    }


    private fun getHorizontalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper? {
        if (horizontalHelper == null) {
            horizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager)
        }
        return horizontalHelper
    }

    override fun createScroller(layoutManager: RecyclerView.LayoutManager?): RecyclerView.SmoothScroller? {
        return if (layoutManager !is RecyclerView.SmoothScroller.ScrollVectorProvider) {
            return null
        } else {
            object : LinearSmoothScroller(recyclerView?.context) {

                override fun onTargetFound(targetView: View, state: RecyclerView.State, action: Action) {
                    val snapDistances = calculateDistanceToFinalSnap(recyclerView!!.layoutManager!!, targetView)
                    val dx = snapDistances!![0]
                    val dy = snapDistances[1]
                    val time = calculateTimeForDeceleration(Math.max(Math.abs(dx), Math.abs(dy)))
                    if (time > 0) {
                        action.update(dx, dy, time, mDecelerateInterpolator)
                    }
                }

                override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics?): Float {
                    return MILLISECONDS_PER_INCH / displayMetrics!!.densityDpi
                }

            }
        }
    }
}