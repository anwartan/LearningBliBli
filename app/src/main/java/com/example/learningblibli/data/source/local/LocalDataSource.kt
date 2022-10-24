package com.example.learningblibli.data.source.local

import androidx.lifecycle.LiveData
import com.example.learningblibli.data.source.local.entity.MealEntity
import com.example.learningblibli.data.source.local.room.MealDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor (private val mealDao: MealDao){

    suspend fun insertMeal(mealEntity: MealEntity) = mealDao.insertMeal(mealEntity)
    suspend fun getFavoriteMealById(id:Int): MealEntity? = mealDao.getMealById(id)
    fun getFavoriteMeals(): LiveData<List<MealEntity>> = mealDao.getFavoriteMeals()
    suspend fun setFavoriteMeal(id:Int, newStatus: Boolean) = mealDao.setFavoriteById(id,newStatus)
}