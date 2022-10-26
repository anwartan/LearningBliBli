package com.example.learningblibli.data.source.remote.network

import com.example.learningblibli.data.source.remote.response.ListMealResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search.php")
    fun getAllMealsByFirstLetter(@Query("f")search:String): Observable<ListMealResponse>

    @GET("lookup.php")
    fun getMealDetail(@Query("i")id:Int):Observable<ListMealResponse>

    @GET("search.php")
    fun searchMeal(@Query("s")search: String):Observable<ListMealResponse>
}