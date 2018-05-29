package com.ijays.kotlinstudy

import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity

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