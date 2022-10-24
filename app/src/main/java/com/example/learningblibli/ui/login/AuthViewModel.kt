package com.example.learningblibli.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.learningblibli.base.BaseViewModel
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.domain.usecase.GetCurrentUserUseCase
import com.example.learningblibli.domain.usecase.LoginByFirebaseUseCase
import com.example.learningblibli.domain.usecase.RegisterByFirebaseUseCase
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val loginByFirebaseUseCase: LoginByFirebaseUseCase,
    private val registerByFirebaseUseCase: RegisterByFirebaseUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
    ) : BaseViewModel() {



    private val _loginFlow = MutableLiveData<Resource<FirebaseUser?>>()
    val loginFlow: LiveData<Resource<FirebaseUser?>> = _loginFlow

    private val _signupFlow = MutableLiveData<Resource<FirebaseUser?>>()
    val signupFlow: LiveData<Resource<FirebaseUser?>> = _signupFlow

    val currentUser:LiveData<FirebaseUser?> get() = getCurrentUserUseCase()

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        _loginFlow.value=Resource.Loading()
        val result = loginByFirebaseUseCase(email, password)
        _loginFlow.value = result
    }
    fun register(email: String, password: String) = viewModelScope.launch {
        _signupFlow.value=Resource.Loading()
        val result = registerByFirebaseUseCase(email, password)
        _signupFlow.value = result
    }
}