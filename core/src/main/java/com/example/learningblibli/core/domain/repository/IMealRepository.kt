package com.example.learningblibli.core.domain.repository

import androidx.lifecycle.LiveData
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.lib_model.model.Meal
import io.reactivex.Observable

interface IMealRepository {
    fun getAllMealsByFirstLetter(firstLetter:String): Observable<Resource<List<Meal>>>
    fun getMealDetail(id:Int):Observable<Resource<Meal>>
    fun searchMeal(name:String): Observable<Resource<List<Meal>>>
    suspend fun setFavoriteMeal(meal: Meal, status:Boolean)
    suspend fun insertFavoriteMeal(meal: Meal)
    suspend fun getFavoriteMealById(id: Int): Meal?
    fun getFavoriteMeals():LiveData<List<Meal>>
}