package com.ijays.kotlinstudy.flow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ijays.kotlinstudy.model.*
import com.ijays.kotlinstudy.network.ApiManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by ijays on 2019/1/14.
 */
class ArticleListViewModel : ViewModel() {

    private lateinit var articleLiveData: MutableLiveData<ResponseModel<MutableList<ArticleInfoModel>>>

    // Create a LiveData
    val bannerLiveData: MutableLiveData<BaseResponseModel<List<BannerModel>>> by lazy {
        MutableLiveData<BaseResponseModel<List<BannerModel>>>()

    }

    fun getArticleListLiveData(id: Int): LiveData<ResponseModel<MutableList<ArticleInfoModel>>> {
        articleLiveData = MutableLiveData()

        loadArticleList(id)
        return articleLiveData
    }

    fun getBannerList() {
        ApiManager.getBannerList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    bannerLiveData.value = it
                }, {

                })
    }

    private fun loadArticleList(id: Int) {
        ApiManager.getArticleList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    articleLiveData.value = it

                }, {
//                    articleLiveData.value=
                })
    }


}
