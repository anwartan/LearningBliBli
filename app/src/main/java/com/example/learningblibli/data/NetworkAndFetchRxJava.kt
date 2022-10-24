package com.example.learningblibli.data

import com.example.learningblibli.data.source.remote.Resource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

abstract class NetworkAndFetchRxJava<ResultType,RequestType> {
    private val result = PublishSubject.create<Resource<ResultType>>()
    private val mCompositeDisposable = CompositeDisposable()
}