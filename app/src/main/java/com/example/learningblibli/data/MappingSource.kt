package com.example.learningblibli.data

import android.util.Log
import com.example.learningblibli.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class MappingSource<Type> {
    private var result :Flow<ApiResponse<Type>> = flow {
        try {
            val response = createCall()
            emit(ApiResponse.Success(response))
        }catch (e:Exception){
            emit(ApiResponse.Error(e.toString()))
            Log.e("RemoteDataSource", e.toString())
        }
    }.flowOn(Dispatchers.IO)

    protected abstract suspend fun createCall(): Type

    fun asFlow(): Flow<ApiResponse<Type>> =result

}