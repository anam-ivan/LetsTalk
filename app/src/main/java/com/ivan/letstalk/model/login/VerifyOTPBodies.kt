package com.ivan.letstalk.model.login

class VerifyOTPBodies {
    data class OTPLoginBody(
        val cr_no: String,
        val otp: String
    )
}