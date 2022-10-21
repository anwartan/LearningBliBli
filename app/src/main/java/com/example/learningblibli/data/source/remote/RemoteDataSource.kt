package com.example.learningblibli.data.source.remote

import com.example.learningblibli.data.source.remote.network.ApiResponse
import com.example.learningblibli.data.source.remote.network.ApiService
import com.example.learningblibli.data.source.remote.response.ListMealResponse
import com.example.learningblibli.utils.MapperSource
import kotlinx.coroutines.flow.Flow


class RemoteDataSource (private val apiService: ApiService){

    suspend fun getAllMealsByFirstLetter(firstLetter:String):Flow<ApiResponse<ListMealResponse>>{
        return MapperSource.map {
            apiService.getAllMealsByFirstLetter(firstLetter)
        }
    }

    suspend fun getMealDetail(id:Int):Flow<ApiResponse<ListMealResponse>>{
        return MapperSource.map {
            apiService.getMealDetail(id)
        }
    }
    suspend fun searchMeal(name:String):Flow<ApiResponse<ListMealResponse>>{
        return MapperSource.map {
            apiService.searchMeal(name)
        }
    }
}