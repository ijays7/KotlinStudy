package com.ijays.kotlinstudy.model

/**
 * Created by ijays on 2018/6/4.
 */

data class ResponseDataInfo<T>(val curPage: Int,
                               val offset: Int,
                               val over: Boolean,
                               val pageCount: Int,
                               val size: Int,
                               val total: Int,
                               var datas: T)