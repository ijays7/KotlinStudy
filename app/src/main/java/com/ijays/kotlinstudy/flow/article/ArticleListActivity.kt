package com.ijays.kotlinstudy.flow.article

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ijays.kotlinstudy.R
import com.ijays.kotlinstudy.extension.setImmersiveMode
import com.ijays.kotlinstudy.flow.browser.BrowserActivity
import com.ijays.kotlinstudy.model.ArticleInfoModel
import com.ijays.kotlinstudy.model.ArticleListAdapter
import com.ijays.kotlinstudy.model.BannerModel
import com.ijays.kotlinstudy.model.ResponseDataInfo
import com.ijays.kotlinstudy.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_article_list_layout.*

/**
 * Created by ijays on 2018/6/4.
 */
class ArticleListActivity : BaseMvpActivity<ArticleListContract.View, ArticleListPresenter>(),
        ArticleListContract.View {


    private var topBarHeight: Int = 0

    private lateinit var mAdapter: ArticleListAdapter

    override var mPresenter: ArticleListPresenter = ArticleListPresenter()


    override fun getLayoutId(): Int {
        return R.layout.activity_article_list_layout
    }

    override fun initView(savedInstanceState: Bundle?) {

        // set immersive mode
        setImmersiveMode(window)

        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,
                false)
        mAdapter = ArticleListAdapter(ArrayList())
        recycler_view.adapter = mAdapter

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mPresenter.getArticleList(0)

        mPresenter.getBannerList()

    }

    override fun handleArticleList(articleResponse: ResponseDataInfo<MutableList<ArticleInfoModel>>?) {
        Log.e("SONGJIE", "handle success")

        if (articleResponse == null) {
            showError("返回数据为空")
            return
        }

        val dataList = articleResponse.datas
        if (!dataList.isEmpty()) {
            mAdapter.getAdapter().addAll(dataList)
            mAdapter.notifyDataSetChanged()

        } else {
            Log.e("SONGJIE", "list为空")
        }
    }

    override fun displayBanner(bannerList: List<BannerModel>) {
        if (bannerList.isNotEmpty()) {
            rv_banner.setRvBannerData(bannerList)
            rv_banner.setOnSwitchRvBannerListener { position, imageView ->
                Glide.with(this)
                        .load(bannerList[position].imagePath)
                        .into(imageView)
            }

            rv_banner.setOnRvBannerClickListener {
                BrowserActivity.startActivity(this, bannerList[it].url)
            }

        }
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true

        } else {
            super.onOptionsItemSelected(item)
        }
    }


}