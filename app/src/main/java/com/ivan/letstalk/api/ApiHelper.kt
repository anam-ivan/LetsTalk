package com.ivan.letstalk.api

import com.ivan.letstalk.model.login.RequestBodies
import com.ivan.letstalk.model.login.VerifyOTPBodies

class ApiHelper (private val apiService: ApiService) {

    suspend fun login(user: RequestBodies.LoginBody) = apiService.loginUser(user)

    suspend fun validateOTP(validateOTP: VerifyOTPBodies.OTPLoginBody) = apiService.validateOTP(validateOTP)
}