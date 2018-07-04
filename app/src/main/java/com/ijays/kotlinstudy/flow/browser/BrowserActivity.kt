package com.ijays.kotlinstudy.flow.browser

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import com.ijays.kotlinstudy.R
import com.ijays.kotlinstudy.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_browser_layout.*

/**
 * Browser Page
 * Created by ijays on 2018/6/17.
 */
class BrowserActivity : BaseMvpActivity<BrowserContract.View, BrowserPresenter>(), BrowserContract.View {

    override var mPresenter: BrowserPresenter = BrowserPresenter()

    companion object {
        const val EXTRA_URL = "extra_url"
        fun startActivity(context: Context, url: String) {
            val intent = Intent(context, BrowserActivity::class.java)
            intent.putExtra(EXTRA_URL, url)
            context.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_browser_layout
    }


    override fun initView(savedInstanceState: Bundle?) {
        val requestUrl = intent.getStringExtra(EXTRA_URL)

        mPresenter.loadUrl(requestUrl)
    }

    override fun setupWebView() {
        val settings = web_view.settings
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        //允许js代码
        settings.javaScriptEnabled = true
        //允许SessionStorage/LocalStorage存储
        settings.domStorageEnabled = true
        //
        settings.displayZoomControls = false
        settings.builtInZoomControls = false

        settings.loadsImagesAutomatically = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        web_view.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)

                progress_bar.progress = newProgress

                if (newProgress >= 90) {
                    progress_bar.visibility = View.GONE
                } else {
                    progress_bar.visibility = View.VISIBLE
                }
            }

        }
    }

    override fun loadUrl(url: String) {
        web_view.loadUrl(url)
    }


    override fun onDestroy() {
        super.onDestroy()

        if (web_view != null) {
            web_view.removeAllViews()
            web_view.clearHistory()
            web_view.destroy()
        }
    }
}