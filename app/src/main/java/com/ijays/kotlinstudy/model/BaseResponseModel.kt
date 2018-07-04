package com.ijays.kotlinstudy.model

/**
 * Created by ijays on 2018/7/4.
 */
data class BaseResponseModel<T>(val errorCode: Int,
                                val errorMsg: String,
                                val data: T) 