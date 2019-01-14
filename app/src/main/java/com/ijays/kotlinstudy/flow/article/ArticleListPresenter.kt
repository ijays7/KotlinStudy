package com.ijays.kotlinstudy.flow.article

import android.util.Log
import com.ijays.kotlinstudy.AppConstants
import com.ijays.kotlinstudy.mvp.BaseMvpPresenterImpl
import com.ijays.kotlinstudy.network.ApiManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * Created by ijays on 2018/6/4.
 */
class ArticleListPresenter(view: ArticleListContract.View) : BaseMvpPresenterImpl<ArticleListContract.View>(view),
        ArticleListContract.Presenter {

    override fun getArticleList(id: Int) {
        ApiManager.getArticleList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    if (it == null) {
                        view?.showError("返回数据为空")
                        return@Consumer
                    }

                    if (it.errorCode < AppConstants.RESPONSE_SUCCESS) {
                        view?.showError(it.errorMsg)
                        return@Consumer
                    }

                    view?.handleArticleList(it.data)
                }, Consumer {
                    Log.e("SONGJIE", "error===>" + it.message)
                    view?.showError(it.message)
                })
    }

    override fun getBannerList() {
        ApiManager.getBannerList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    view?.displayBanner(bannerList = it.data)
                }, {

                })

    }

}