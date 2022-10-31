package com.example.learningblibli.core.domain.repository

import androidx.lifecycle.LiveData
import com.example.learningblibli.core.data.source.local.entity.MealEntity
import com.example.learningblibli.lib_model.response.ListMealResponse
import io.reactivex.Observable

interface IMealRepository {
    fun getAllMealsByFirstLetter(firstLetter: String): Observable<ListMealResponse>
    fun getMealDetail(id: Int): Observable<ListMealResponse>
    fun searchMeal(name: String): Observable<ListMealResponse>
    suspend fun setFavoriteMeal(mealEntity: MealEntity, status: Boolean)
    suspend fun insertFavoriteMeal(mealEntity: MealEntity)
    suspend fun getFavoriteMealById(id: Int): MealEntity?
    fun getFavoriteMeals(): LiveData<List<MealEntity>>
}