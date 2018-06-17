package com.ijays.kotlinstudy.network

import com.ijays.kotlinstudy.model.ArticleInfoModel
import com.ijays.kotlinstudy.model.ResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*


/**
 * Created by ijaysdev on 20/08/2017.
 */
interface ApiService {

    @GET("article/list/{id}/json")
    fun getArticleList(@Path("id") id: Int): Observable<ResponseModel<MutableList<ArticleInfoModel>>>
}