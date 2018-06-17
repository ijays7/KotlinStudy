package com.ijays.kotlinstudy.mvp

/**
 * Created by ijays on 2018/6/2.
 */
interface BaseMvpPresenter<in V : BaseMvpView> {
    fun attachView(view: V)

    fun detachView()
}