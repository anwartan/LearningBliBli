package com.example.learningblibli.data

import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

abstract class NetworkAndFetch<ResultType,RequestType> {
    private var result : Flow<Resource<ResultType>> = flow {

        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success ->{
                onFetchSuccess(apiResponse.data)
                emit(Resource.Success(mapResponse(apiResponse.data)))
            }
            is ApiResponse.Error -> {
                onFetchFailed()
                emit(Resource.Error(apiResponse.errorMessage))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Error("EMPTY"))
            }
        }
    }
    protected open fun onFetchFailed() {}

    protected open suspend fun onFetchSuccess(data: RequestType) {}

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract fun mapResponse(data: RequestType): ResultType

    fun asFlow(): Flow<Resource<ResultType>> =result

}