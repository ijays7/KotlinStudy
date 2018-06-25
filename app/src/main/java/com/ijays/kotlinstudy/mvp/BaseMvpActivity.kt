package com.ijays.kotlinstudy.mvp

import android.content.Context
import android.os.Bundle
import com.ijays.kotlinstudy.BaseActivity
import com.ijays.kotlinstudy.util.ToastUtil

/**
 * Created by ijays on 2018/6/2.
 */
abstract class BaseMvpActivity<in V : BaseMvpView, T : BaseMvpPresenter<V>> : BaseActivity(), BaseMvpView {
    protected abstract var mPresenter: T


    override fun onCreate(savedInstanceState: Bundle?) {
        this.mPresenter.attachView(view = this as V)
        super.onCreate(savedInstanceState)
    }

    override fun getContext(): Context = this

    override fun showError(error: String?) {
        ToastUtil.showShort(applicationContext, error)
    }

    override fun showError(stringResId: Int) {
        ToastUtil.showShort(applicationContext, getString(stringResId))
    }

    override fun showMessage(stringResId: Int) {
    ToastUtil.showShort(applicationContext,getString(stringResId))
    }

    override fun showMessage(message:String) {
    }

    override fun onStop() {
        super.onStop()
        mPresenter.detachView()
    }

}