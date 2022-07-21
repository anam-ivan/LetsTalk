package com.ivan.letstalk.api

import com.ivan.letstalk.model.login.RequestBodies
import com.ivan.letstalk.model.login.VerifyOTPBodies
import com.ivan.letstalk.model.phonenumberupdate.PhoneNumberChangeBodies

class ApiHelper (private val apiService: ApiService) {

    suspend fun login(user: RequestBodies.LoginBody) = apiService.loginUser(user)

    suspend fun validateOTP(validateOTP: VerifyOTPBodies.OTPLoginBody) = apiService.validateOTP(validateOTP)

    suspend fun patientProfileDetails() = apiService.patientProfileDetails()

    suspend fun patientPhoneNumberUpdate(phoneNumberUpdate: PhoneNumberChangeBodies.PhoneNumberUpdateBody) = apiService.patientPhoneChangeRequest(phoneNumberUpdate)
}