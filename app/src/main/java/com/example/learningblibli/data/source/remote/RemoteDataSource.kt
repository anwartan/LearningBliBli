package com.example.learningblibli.data.source.remote

import android.util.Log
import com.example.learningblibli.data.source.remote.network.ApiResponse
import com.example.learningblibli.data.source.remote.network.ApiService
import com.example.learningblibli.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class RemoteDataSource (private val apiService: ApiService){


    suspend fun getNowPlayingMovies(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getNowPlayingMovies()
                val dataResponse = response.results
                if(dataResponse.isEmpty()){
                    emit(ApiResponse.Empty)
                }else{
                    emit(ApiResponse.Success(dataResponse))
                }
            }catch (e:Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    }

    suspend fun getMovieDetail(id: Int):Flow<ApiResponse<MovieResponse>>{
        return flow{
            try {
                val response = apiService.getMovieDetail(id)
                emit(ApiResponse.Success(response))
            }catch (e:Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())

            }
        }.flowOn(Dispatchers.IO)
    }
}