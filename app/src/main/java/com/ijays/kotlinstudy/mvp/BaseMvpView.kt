package com.ijays.kotlinstudy.mvp

import android.content.Context
import androidx.annotation.StringRes

/**
 * Created by ijays on 2018/6/2.
 */
interface BaseMvpView {

    fun getContext(): Context

    fun showError(error: String?)

    fun showError(@StringRes stringResId: Int)

    fun showMessage(@StringRes stringResId: Int)

    fun showMessage(message:String)
}