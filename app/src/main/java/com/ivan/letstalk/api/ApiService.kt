package com.ivan.letstalk.api

import com.ivan.letstalk.model.login.LoginResponse
import com.ivan.letstalk.model.login.RequestBodies
import com.ivan.letstalk.model.login.VerifyOTPBodies
import com.ivan.letstalk.model.phonenumberupdate.PhoneNumberChangeBodies
import com.ivan.letstalk.model.profile.PatientProfileResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("applogin")
    suspend fun loginUser(@Body body: RequestBodies.LoginBody): Response<LoginResponse>

    @POST("validateotp")
    suspend fun validateOTP(@Body body: VerifyOTPBodies.OTPLoginBody): Response<LoginResponse>

    @POST("user_details")
    suspend fun patientProfileDetails(): Response<PatientProfileResponse>

    @POST("VerifyPhoneChangeRequest")
    suspend fun patientPhoneChangeRequest(@Body body: PhoneNumberChangeBodies.PhoneNumberUpdateBody): Response<LoginResponse>
}
