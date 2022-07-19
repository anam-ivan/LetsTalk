package com.ivan.letstalk.api

import com.ivan.letstalk.model.login.LoginResponse
import com.ivan.letstalk.model.login.RequestBodies
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun loginUser(@Body body: RequestBodies.LoginBody): Response<LoginResponse>
}
