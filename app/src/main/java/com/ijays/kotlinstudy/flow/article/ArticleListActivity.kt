package com.ijays.kotlinstudy.flow.article

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
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
import kotlin.math.abs

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

        setRVListener()

        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false)
        mAdapter = ArticleListAdapter(ArrayList())
        recycler_view.adapter = mAdapter

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
            rv_banner.setOnSwitchRvBannerListener({ position, imageView ->
                Glide.with(this)
                        .load(bannerList[position].imagePath)
                        .into(imageView)
            })

            rv_banner.setOnRvBannerClickListener {
                BrowserActivity.startActivity(this, bannerList[it].url)
            }

        }
    }

    private fun setRVListener() {

        ll_top_bar.post({
            topBarHeight = ll_top_bar.measuredHeight
        })

//        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//
//            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//            }
//
//            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//
//                Log.e("SONGJIE", "---dy---$dy")
//                Log.e("SONGJIE", "---topBarHeight---$topBarHeight")
//                if (dy < topBarHeight) {
//                    status_bar_view.alpha = (dy / topBarHeight).toFloat()
//                }else{
//                    status_bar_view.alpha=.95f
//                }
//            }
//        })

        app_bar.addOnOffsetChangedListener({ v, verticalOffset ->
            Log.e("SONGJIE", "-offset is-->$verticalOffset--------->" + topBarHeight)

            if (verticalOffset == 0) {
                return@addOnOffsetChangedListener
            }
            if (abs(verticalOffset) < topBarHeight) {
                Log.e("SONGJIE", "<<<<<<<")
                status_bar_view.alpha = (abs(verticalOffset) / topBarHeight).toFloat()
                toolbar_bg.alpha = (abs(verticalOffset) / topBarHeight).toFloat()
            } else {
                Log.e("SONGJIE", "======")
                status_bar_view.alpha = 1f
                toolbar_bg.alpha = 1f
            }

        })
    }


}