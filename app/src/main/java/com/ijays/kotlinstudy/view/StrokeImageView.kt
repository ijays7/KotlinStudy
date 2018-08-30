package com.ijays.kotlinstudy.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageView
import com.ijays.kotlinstudy.R
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

/**
 * Created by ijays on 2018/8/16.
 */
class StrokeImageView(context: Context, attr: AttributeSet?, def: Int = 0) : ImageView(context, attr, def) {

    private var paint: Paint? = null

    private var mWidth: Int = 0

    private var mHeight: Int = 0

    private var rectF: RectF? = null

    private var currentAngle = 0f

    private var disposable: Disposable? = null

    private var ps: PublishSubject<Long> = PublishSubject.create<Long>()


    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attr: AttributeSet) : this(context, attr, 0)

    init {
        initPaint()
        setImageResource(R.mipmap.ic_launcher_round)
    }

    private fun initPaint() {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint?.color = ContextCompat.getColor(context, R.color.google_blue)
        paint?.style = Paint.Style.STROKE
        paint?.strokeWidth = 8f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val centerX = (w / 2 - paddingLeft - paddingRight).toFloat()
        val centerY = (h / 2 - paddingTop - paddingBottom).toFloat()

        val radius = Math.min(centerX, centerY) - 10

        rectF = RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawArc(rectF, 270f, currentAngle, false, paint)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        val actionMusked = event?.actionMasked ?: 0
        when (actionMusked) {
            MotionEvent.ACTION_DOWN -> {
                Log.e("SONGJIE", "ACTION_DOWN")
                com.ijays.kotlinstudy.util.AudioManager.setupAudio()

                disposable = Flowable.intervalRange(0, 60, 0, 1, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            Log.e("SONGJIE", "LONG-->$it")
                            currentAngle = it.toFloat() / 60 * 360
                            invalidate()


                        }
            }
            MotionEvent.ACTION_UP -> {
                Log.e("SONGJIE", "ACTION_UP")
                disposable?.dispose()
                currentAngle = 0f
                invalidate()
            }
        }

        return true
    }


}