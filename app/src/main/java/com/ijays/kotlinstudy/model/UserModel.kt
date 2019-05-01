package com.ijays.kotlinstudy.model

import java.io.Serializable

/**
 * Created by ijays on 2019-05-01.
 */
data class UserModel(
        var userId: String,
        var userName: String,
        var headImage: String?,
        var gender: Int? = 0
) : Serializable