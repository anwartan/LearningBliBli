package com.example.learningblibli.feature_detail.utils

import com.example.learningblibli.core.base.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestCoroutineProvider: CoroutineContextProvider() {
    override val IO: CoroutineContext
        get() = Dispatchers.Unconfined
    override val Main: CoroutineContext
        get() = Dispatchers.Unconfined
}