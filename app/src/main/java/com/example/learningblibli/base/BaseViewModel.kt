package com.example.learningblibli.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel:ViewModel() {
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _loading = MutableLiveData<Boolean?>()
    val loading: LiveData<Boolean?> = _loading

}