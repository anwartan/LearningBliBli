package com.example.learningblibli.domain.repository

import androidx.lifecycle.LiveData
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.domain.model.Meal
import kotlinx.coroutines.flow.Flow

interface IMealRepository {
    fun getAllMealsByFirstLetter(firstLetter:String): Flow<Resource<List<Meal>>>
    fun getMealDetail(id:Int):Flow<Resource<Meal>>
    fun searchMeal(name:String): Flow<Resource<List<Meal>>>
    suspend fun setFavoriteMeal(meal: Meal,status:Boolean)
    suspend fun insertFavoriteMeal(meal:Meal)
    suspend fun getFavoriteMealById(id: Int):Meal?
    fun getFavoriteMeals():LiveData<List<Meal>>
}