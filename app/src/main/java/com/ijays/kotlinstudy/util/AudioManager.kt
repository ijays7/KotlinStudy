package com.ijays.kotlinstudy.util

import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Environment
import android.util.Log
import java.io.File

/**
 * Created by ijays on 2018/8/21.
 */
object AudioManager {
    private var audioFilePath: String = ""

    private var mediaRecorder: MediaRecorder? = null

    fun setupAudio() {
        mediaRecorder = MediaRecorder()

        try {
            mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)

            mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)

            mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            audioFilePath = Environment.getExternalStorageDirectory().absolutePath + "/kotlinStudy"

            Log.e("SONGJIE", "===path==$audioFilePath")
            val file = File(audioFilePath)
            if (!file.exists()) {
                file.mkdir()
            }

            audioFilePath = File(file, "/test.mp3").absolutePath
            mediaRecorder?.setOutputFile(audioFilePath)
            mediaRecorder?.prepare()

            mediaRecorder?.start()
        } catch (e: Exception) {
            Log.e("SONGJIE", "e==>${e.message}")
        }


    }

    fun getFilePath(): String {
        return audioFilePath
    }


    fun release() {
        mediaRecorder?.stop()
        mediaRecorder?.release()
        mediaRecorder = null
    }


}
