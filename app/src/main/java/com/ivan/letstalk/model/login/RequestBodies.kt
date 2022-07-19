package com.ivan.letstalk.model.login

object RequestBodies {
    data class LoginBody(
        val email_id:String,
        val password:String
    )
}