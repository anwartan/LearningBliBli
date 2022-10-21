package com.example.learningblibli.utils

import android.util.Log
import com.example.learningblibli.data.source.remote.network.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

object MapperSource {
   fun <T>map(createCall:suspend ()->T): Flow<ApiResponse<T>> =  flow {
        try {
            val response = createCall()
            emit(ApiResponse.Success(response))
        }catch (e:Exception){
            emit(ApiResponse.Error(e.toString()))
            Log.e("RemoteDataSource", e.toString())
        }
    }.flowOn(Dispatchers.IO)
}