package com.example.learningblibli.data

import android.annotation.SuppressLint
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.data.source.remote.network.ApiResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

abstract class NetworkAndFetchRxJava<ResultType,RequestType> : DisposableObserver<ResultType>() {

    private val result = PublishSubject.create<Resource<ResultType>> { obs->
        val apiResource = createCall()
        apiResource
    }

    override fun onNext(t: ResultType) {

    }

    override fun onError(e: Throwable) {

    }

    override fun onComplete() {

    }

    protected open fun onFetchFailed(){}

    protected abstract fun mapResponse(data: RequestType): ResultType

    protected open fun onFetchSuccess(data: RequestType){}

    protected abstract fun createCall(): Observable<ApiResponse<RequestType>>

    fun asObservable():Observable<Resource<ResultType>> = result

}