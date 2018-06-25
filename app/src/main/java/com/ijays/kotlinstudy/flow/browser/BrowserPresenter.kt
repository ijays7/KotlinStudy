package com.ijays.kotlinstudy.flow.browser

import com.ijays.kotlinstudy.mvp.BaseMvpPresenterImpl

/**
 * Created by ijays on 2018/6/25.
 */
class BrowserPresenter : BaseMvpPresenterImpl<BrowserContract.View>(), BrowserContract.Presenter {
    override fun loadUrl(url: String) {
        mView?.setupWebView()

        mView?.loadUrl(url)
    }

}