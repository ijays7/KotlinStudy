package com.ijays.kotlinstudy.network

import com.ijays.kotlinstudy.AppConstants
import com.ijays.kotlinstudy.model.ArticleInfoModel
import com.ijays.kotlinstudy.model.ResponseModel
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by ijaysdev on 20/08/2017.
 */
object ApiManager {
    private lateinit var apiSerive: ApiService

    init {
        val retrofit = initRetrofit()
        initService(retrofit)
    }

    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }

    private fun initService(retrofit: Retrofit) {
        apiSerive = retrofit.create(ApiService::class.java)
    }

    fun getArticleList(id: Int): Observable<ResponseModel<MutableList<ArticleInfoModel>>> {
        return apiSerive.getArticleList(id)
    }

}