package com.example.learningblibli.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.learningblibli.core.base.BaseViewModel
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.core.domain.usecase.GetCurrentUserUseCase
import com.example.learningblibli.core.domain.usecase.LoginByFirebaseUseCase
import com.example.learningblibli.core.domain.usecase.LogoutByFirebaseUseCase
import com.example.learningblibli.core.domain.usecase.RegisterByFirebaseUseCase
import com.example.learningblibli.lib_model.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val loginByFirebaseUseCase: LoginByFirebaseUseCase,
    private val registerByFirebaseUseCase: RegisterByFirebaseUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val logoutByFirebaseUseCase: LogoutByFirebaseUseCase
    ) : BaseViewModel() {
    private var _currentUser = MutableLiveData<User?>(null)
    val currentUser:LiveData<User?> get() = _currentUser



    fun getCurrentUser()  = viewModelScope.launch {
        _currentUser.postValue(getCurrentUserUseCase())
    }

    private val _loginFlow = MutableLiveData<Resource<User?>>()


    fun loginUser(email: String, password: String) = viewModelScope.launch {
        setLoading(true)
        val result = loginByFirebaseUseCase(email, password)
        _loginFlow.value = result
        getCurrentUser()
        setLoading(false)
    }


    private val _signupFlow = MutableLiveData<Resource<User?>>()
    val signupFlow: LiveData<Resource<User?>> = _signupFlow

    fun register(email: String, password: String) = viewModelScope.launch {
        setLoading(true)
        val result = registerByFirebaseUseCase(email, password)
        _signupFlow.value = result
        setLoading(false)
    }

    fun logout() = viewModelScope.launch{
        setLoading(true)
        delay(1000)
        logoutByFirebaseUseCase()
        getCurrentUser()
        setLoading(false)
    }
}