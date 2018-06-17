package com.ijays.kotlinstudy.util

import android.app.Activity
import android.content.Context
import android.widget.Toast

/**
 * Created by ijays on 2018/5/29.
 */

object ToastUtil {
    fun showShort(context: Context, msg: String?) {
        var ctx = context
        if (ctx is Activity) {
            ctx = context.applicationContext
        }
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
    }

    fun showLong(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }
}
