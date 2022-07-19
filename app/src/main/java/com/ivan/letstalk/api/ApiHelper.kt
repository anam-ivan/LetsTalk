package com.ivan.letstalk.api

import com.ivan.letstalk.model.login.RequestBodies

class ApiHelper (private val apiService: ApiService) {

    suspend fun login(user: RequestBodies.LoginBody) = apiService.loginUser(user)
}