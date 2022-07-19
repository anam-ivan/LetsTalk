package com.ivan.letstalk.repo

import com.ivan.letstalk.api.ApiHelper
import com.ivan.letstalk.model.login.RequestBodies

class LoginRepository(private val apiHelper: ApiHelper) {
    suspend fun loginPatient(user: RequestBodies.LoginBody) = apiHelper.login(user)
}