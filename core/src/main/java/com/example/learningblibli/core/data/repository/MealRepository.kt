package com.example.learningblibli.core.data.repository

import androidx.lifecycle.LiveData
import com.example.learningblibli.core.data.source.local.entity.MealEntity
import com.example.learningblibli.core.data.source.local.room.MealDao
import com.example.learningblibli.core.domain.repository.IMealRepository
import com.example.learningblibli.lib_api.service.ApiService
import com.example.learningblibli.lib_model.response.ListMealResponse
import io.reactivex.Observable
import javax.inject.Inject

class MealRepository @Inject constructor(private val apiService: ApiService, private val mealDao: MealDao):
    IMealRepository {

    override fun getAllMealsByFirstLetter(firstLetter: String): Observable<ListMealResponse> {
        return apiService.getAllMealsByFirstLetter(firstLetter)
    }

    override fun getMealDetail(id: Int): Observable<ListMealResponse> {
        return apiService.getMealDetail(id)
    }

    override fun searchMeal(name: String): Observable<ListMealResponse> {
        return apiService.searchMeal(name)
    }


    override suspend fun setFavoriteMeal(mealEntity: MealEntity, status: Boolean) {
        mealDao.setFavoriteById(mealEntity.idMeal,status)
    }

    override suspend fun insertFavoriteMeal(mealEntity: MealEntity) {
        mealDao.insertMeal(mealEntity)
    }

    override suspend fun getFavoriteMealById(id: Int): MealEntity? {
        return  mealDao.getMealById(id)
    }

    override fun getFavoriteMeals(): LiveData<List<MealEntity>> {
        return mealDao.getFavoriteMeals()
    }
}