package com.ivan.letstalk.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ivan.letstalk.helper.Resource
import com.ivan.letstalk.model.login.RequestBodies
import com.ivan.letstalk.repo.LoginRepository
import kotlinx.coroutines.Dispatchers

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    fun getLogin(user: RequestBodies.LoginBody) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = loginRepository.loginPatient(user)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}