package com.ijays.kotlinstudy.extension

import java.util.concurrent.TimeUnit

/**
 * Created by ijays on 2018/6/5.
 */


/**
 * 将时间戳转换成 xx小时前 的样式（同微信）
 *
 * @return
 *
 * 如果小于1秒钟内，显示刚刚
 * 如果在1分钟内，显示xx秒前
 * 如果在1小时内，显示xx分钟前
 * 如果是昨天，显示昨天
 * 如果在一个月内，显示xx天前
 * 如果在一年内，显示xx月前
 * 如果在两年内，显示xx年前
 * 其余显示，2017-09-01
 */

fun Long.parseTimeStamp2String(ts: Long): String {

    val currentTime = currentTime()
    val span = currentTime - ts
    return when {
        span <= TimeUnit.SECONDS.toMillis(1) -> "刚刚"
        span <= TimeUnit.MINUTES.toMillis(1) -> String.format("%d秒前", span / TimeUnit.SECONDS.toMillis(1))
        span <= TimeUnit.HOURS.toMillis(1) -> String.format("%d分钟前", span / TimeUnit.MINUTES.toMillis(1))
        span <= TimeUnit.DAYS.toMillis(1) -> String.format("%d小时前", span / TimeUnit.HOURS.toMillis(1))
        span >= TimeUnit.DAYS.toMillis(1) && span <= TimeUnit.DAYS.toMillis(1) * 2 -> "昨天"
        span <= TimeUnit.DAYS.toMillis(1) * 30 -> String.format("%d天前", span / TimeUnit.DAYS.toMillis(1))
        span <= TimeUnit.DAYS.toMillis(1) * 30 * 12 -> String.format("%d月前", span / (TimeUnit.DAYS.toMillis(1) * 30))
        span <= TimeUnit.DAYS.toMillis(1) * 30 * 12 * 2 -> String.format("%d年前", span / (TimeUnit.DAYS.toMillis(1) * 30 * 12))
        else -> String.format("%tF", this)
    }
}

fun currentTime(): Long {
    return System.currentTimeMillis()
}
