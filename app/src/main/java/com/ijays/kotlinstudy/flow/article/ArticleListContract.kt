package com.ijays.kotlinstudy.flow.article

import com.ijays.kotlinstudy.model.ArticleInfoModel
import com.ijays.kotlinstudy.model.BannerModel
import com.ijays.kotlinstudy.model.ResponseDataInfo
import com.ijays.kotlinstudy.mvp.BaseMvpPresenter
import com.ijays.kotlinstudy.mvp.BaseMvpView

/**
 * Created by ijays on 2018/6/4.
 */
class ArticleListContract {
    interface View : BaseMvpView {
        fun handleArticleList(articleResponse: ResponseDataInfo<MutableList<ArticleInfoModel>>?)

        fun displayBanner(bannerList:List<BannerModel>)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun getArticleList(id: Int)

        fun getBannerList()
    }
}