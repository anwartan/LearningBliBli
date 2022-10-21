package com.example.learningblibli.data.source.local

import com.example.learningblibli.data.source.local.entity.MealEntity
import com.example.learningblibli.data.source.local.room.MealDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource (private val mealDao: MealDao){

    suspend fun insertMeal(mealEntity: MealEntity){
        mealDao.insertMeal(mealEntity)
    }
    fun getMealById(id:Int):Flow<MealEntity?> = mealDao.getMealById(id)
    fun getFavoriteMeals(): Flow<List<MealEntity>> = mealDao.getFavoriteMeals()
    fun setFavoriteMeal(id:Int, newStatus: Boolean) = mealDao.setFavoriteById(id,newStatus)
}