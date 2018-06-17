package com.ijays.kotlinstudy.model

/**
 * Created by ijays on 2018/6/4.
 */
data class ResponseModel<T>(val errorCode: Int, val errorMsg: String, var data: ResponseDataInfo<T>)
