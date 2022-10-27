package com.example.learningblibli.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel:ViewModel() {

    private val compositeDisposable = CompositeDisposable()


    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun setLoading(boolean: Boolean){
        _loading.postValue(boolean)
    }

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}