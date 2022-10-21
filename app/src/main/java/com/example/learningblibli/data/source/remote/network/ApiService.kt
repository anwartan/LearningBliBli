package com.example.learningblibli.data.source.remote.network

import com.example.learningblibli.data.source.remote.response.ListMealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search.php")
    suspend fun getAllMealsByFirstLetter(@Query("f")search:String):ListMealResponse

    @GET("lookup.php")
    suspend fun getMealDetail(@Query("i")id:Int):ListMealResponse

    @GET("search.php")
    suspend fun searchMeal(@Query("s")search: String):ListMealResponse
}