package com.ijays.kotlinstudy.flow.article

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.ijays.kotlinstudy.R
import com.ijays.kotlinstudy.model.ArticleInfoModel
import com.ijays.kotlinstudy.model.ArticleListAdapter
import com.ijays.kotlinstudy.model.ResponseDataInfo
import com.ijays.kotlinstudy.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_article_list_layout.*

/**
 * Created by ijays on 2018/6/4.
 */
class ArticleListActivity : BaseMvpActivity<ArticleListContract.View, ArticleListPresenter>(),
        ArticleListContract.View {

    private lateinit var mAdapter: ArticleListAdapter

    override var mPresenter: ArticleListPresenter = ArticleListPresenter()


    override fun getLayoutId(): Int {
        return R.layout.activity_article_list_layout
    }

    override fun initView(savedInstanceState: Bundle?) {

        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false)
        mAdapter = ArticleListAdapter(ArrayList())
        recycler_view.adapter = mAdapter

        mPresenter.getArticleList(0)

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
            Log.e("SONGJIE","list为空")
        }
    }


}