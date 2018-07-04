package com.ijays.kotlinstudy.extension

import android.graphics.Color
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager

/**
 * Created by ijays on 2018/5/29.
 */
fun AppCompatActivity.checkSelfPermissionCompat(permission: String): Int {
    return ActivityCompat.checkSelfPermission(this, permission)
}

fun AppCompatActivity.requestPermissionCompat(permission: Array<String>, requestCode: Int) {
    ActivityCompat.requestPermissions(this, permission, requestCode)
}

fun AppCompatActivity.shouldShowRequestPermissionRationalCompat(permission: String): Boolean {
    return ActivityCompat.shouldShowRequestPermissionRationale(this, permission)
}

fun AppCompatActivity.setImmersiveMode(window: Window) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
    }

}