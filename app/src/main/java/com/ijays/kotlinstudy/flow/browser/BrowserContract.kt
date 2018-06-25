package com.ijays.kotlinstudy.flow.browser

import com.ijays.kotlinstudy.mvp.BaseMvpPresenter
import com.ijays.kotlinstudy.mvp.BaseMvpView

/**
 * Created by ijays on 2018/6/24.
 */
class BrowserContract {
    interface View : BaseMvpView {
        fun setupWebView()

        fun loadUrl(url:String)
    }

    interface Presenter : BaseMvpPresenter<View> {
        /**
         *加载WebViewUrl
         */
        fun loadUrl(url: String)
    }


}