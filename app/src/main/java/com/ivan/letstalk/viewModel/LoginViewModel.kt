package com.ivan.letstalk.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ivan.letstalk.helper.Resource
import com.ivan.letstalk.model.login.RequestBodies
import com.ivan.letstalk.model.login.VerifyOTPBodies
import com.ivan.letstalk.repo.LoginRepository
import kotlinx.coroutines.Dispatchers

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    val crNo: MutableLiveData<String> = MutableLiveData()
    // val otp: MutableLiveData<String> = MutableLiveData()

    fun getLogin(user: RequestBodies.LoginBody) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = loginRepository.loginPatient(user)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun validateOTP(validateOTP: VerifyOTPBodies.OTPLoginBody) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = loginRepository.validateOTP(validateOTP)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}