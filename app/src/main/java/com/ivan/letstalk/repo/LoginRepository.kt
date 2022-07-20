package com.ivan.letstalk.repo

import com.ivan.letstalk.api.ApiHelper
import com.ivan.letstalk.model.login.RequestBodies
import com.ivan.letstalk.model.login.VerifyOTPBodies

class LoginRepository(private val apiHelper: ApiHelper) {
    suspend fun loginPatient(user: RequestBodies.LoginBody) = apiHelper.login(user)

    suspend fun validateOTP(validateOTP: VerifyOTPBodies.OTPLoginBody) = apiHelper.validateOTP(validateOTP)
}