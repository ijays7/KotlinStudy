package com.ijays.kotlinstudy.activity

import android.os.Bundle
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import com.ijays.kotlinstudy.BaseActivity
import com.ijays.kotlinstudy.R
import com.ijays.kotlinstudy.util.AudioManager
import com.ijays.kotlinstudy.util.MediaPlayerManager
import kotlinx.android.synthetic.main.activity_stroke_image_view.*

/**
 * Created by ijays on 2018/8/16.
 */
class StrokeImageActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_stroke_image_view

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

        bt_play.setOnClickListener {
            MediaPlayerManager.playOnlineAudio(AudioManager.getFilePath())
        }

        bt_stop.setOnClickListener {
            com.ijays.kotlinstudy.util.AudioManager.release()
        }

        val text = "907 分"

        val ss = SpannableString(text)
        ss.setSpan(RelativeSizeSpan(.6f), text.length - 1, text.length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv.text = ss

    }

}