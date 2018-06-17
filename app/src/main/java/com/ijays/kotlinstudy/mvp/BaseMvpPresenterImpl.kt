package com.ijays.kotlinstudy.mvp

/**
 * Created by ijays on 2018/6/2.
 */
open class BaseMvpPresenterImpl<V : BaseMvpView> : BaseMvpPresenter<V> {

    protected var mView: V? = null

    override fun attachView(view: V) {
        this.mView = view
    }

    override fun detachView() {
        this.mView = null
    }

}