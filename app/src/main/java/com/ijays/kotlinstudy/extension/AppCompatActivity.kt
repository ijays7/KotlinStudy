package com.ijays.kotlinstudy.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager

/**
 * Created by ijays on 2018/5/29.
 */

/**
 * 扩展属性
 */
val Number.dp: Int get() = (toInt() * Resources.getSystem().displayMetrics.density).toInt()

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

/**
 * Quickly start an Activity
 */
inline fun <reified T : AppCompatActivity> Activity.start(context: Context) {
    startActivity(Intent(context, T::class.java))
}