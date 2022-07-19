package com.ivan.letstalk.model.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    /*@SerializedName("token")
    val token: String,*/
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("status")
    var status: String? = null
)