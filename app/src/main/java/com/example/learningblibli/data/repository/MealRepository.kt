package com.example.learningblibli.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.learningblibli.data.source.local.room.MealDao
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.lib_api.service.ApiService
import com.example.learningblibli.domain.model.Meal
import com.example.learningblibli.domain.repository.IMealRepository
import com.example.learningblibli.utils.mapper.MealMapper
import io.reactivex.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class MealRepository @Inject constructor(private val apiService: ApiService, private val mealDao: MealDao):IMealRepository {

    override fun getAllMealsByFirstLetter(firstLetter: String): Observable<Resource<List<Meal>>> {
        return apiService.getAllMealsByFirstLetter(firstLetter).map {
            if(it.meals==null || it.meals.orEmpty().isEmpty()){
                Resource.Error("EMPTY")
            }else{
                Resource.Success(MealMapper.mapListMealResponseToListMeal(it))
            }
        }.onErrorReturn {
            Resource.Error(it.message?:"SERVER ERROR")
        }
    }

    override fun getMealDetail(id: Int): Observable<Resource<Meal>> {
        return apiService.getMealDetail(id)
            .map {
               if(it.meals==null ||  it.meals.orEmpty().isEmpty()){
                   Resource.Error("EMPTY")
               }else{
                   Resource.Success(MealMapper.mapListMealResponseToListMeal(it)[0])
               }
            }.onErrorReturn {
                Resource.Error(it.message?:"SERVER ERROR")
            }
    }

    override fun searchMeal(name: String): Observable<Resource<List<Meal>>> {
        return apiService.searchMeal(name)
            .map {
                if(it.meals==null || it.meals.orEmpty().isEmpty()){
                    Resource.Error("EMPTY")
                }else{
                    Resource.Success(MealMapper.mapListMealResponseToListMeal(it))
                }
            }.onErrorReturn {
                Resource.Error(it.message?:"SERVER ERROR")
            }
    }


    override suspend fun setFavoriteMeal(meal: Meal, status: Boolean) {
        mealDao.setFavoriteById(meal.idMeal,status)
    }

    override suspend fun insertFavoriteMeal(meal: Meal) {

            val mealEntity=MealMapper.mapModelToEntity(meal)
            mealEntity.isFavorite=true
            mealDao.insertMeal(mealEntity)

    }

    override suspend fun getFavoriteMealById(id: Int): Meal? {
        return  mealDao.getMealById(id)?.let {
            MealMapper.mapEntityToModel(it)
        }
    }

    override fun getFavoriteMeals(): LiveData<List<Meal>> {
        return Transformations.map(mealDao.getFavoriteMeals()) {
            MealMapper.mapEntitiesToModels(it)
        }
    }
}