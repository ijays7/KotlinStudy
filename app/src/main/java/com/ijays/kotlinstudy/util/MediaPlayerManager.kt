package com.ijays.kotlinstudy.util

import android.media.MediaPlayer
import android.text.TextUtils

/**
 * Created by ijays on 2018/8/21.
 */
object MediaPlayerManager {
    private var mediaPlayer: MediaPlayer? = null

    fun playOnlineAudio(audioPath: String) {
        if (TextUtils.isEmpty(audioPath)) {
            return
        }
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setDataSource(audioPath)
        mediaPlayer?.setOnPreparedListener {
            it.start()
        }
        mediaPlayer?.setOnCompletionListener {
            mediaPlayer?.release()
            mediaPlayer = null
        }
        mediaPlayer?.prepareAsync()
    }


}
