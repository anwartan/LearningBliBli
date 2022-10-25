package com.example.learningblibli.data.source.remote

import android.util.Log
import com.example.learningblibli.data.source.remote.network.ApiResponse
import com.example.learningblibli.data.source.remote.network.ApiService
import com.example.learningblibli.data.source.remote.response.ListMealResponse
import com.example.learningblibli.utils.MapperSource
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RemoteDataSource @Inject constructor (private val apiService: ApiService){

    fun getAllMealsByFirstLetter(firstLetter:String): Observable<ApiResponse<ListMealResponse>> {
        val resultData = PublishSubject.create<ApiResponse<ListMealResponse>>()
        val client = apiService.getAllMealsByFirstLetter(firstLetter)
        client

            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ response ->
                if(response==null){
                    resultData.onNext(ApiResponse.Empty)
                }else{
                    resultData.onNext(ApiResponse.Success(response))
                }
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })
        return resultData
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