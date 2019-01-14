package com.ijays.kotlinstudy.flow.browser

import com.ijays.kotlinstudy.mvp.BaseMvpPresenterImpl

/**
 * Created by ijays on 2018/6/25.
 */
class BrowserPresenter(view: BrowserContract.View) : BaseMvpPresenterImpl<BrowserContract.View>(view = view),
        BrowserContract.Presenter {
    override fun loadUrl(url: String) {
        view?.setupWebView()

        view?.loadUrl(url)
    }

}