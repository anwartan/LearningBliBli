package com.example.learningblibli.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.learningblibli.data.NetworkAndFetch
import com.example.learningblibli.data.source.local.LocalDataSource
import com.example.learningblibli.data.source.remote.RemoteDataSource
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.data.source.remote.network.ApiResponse
import com.example.learningblibli.data.source.remote.response.ListMealResponse
import com.example.learningblibli.domain.model.Meal
import com.example.learningblibli.domain.repository.IMealRepository
import com.example.learningblibli.utils.mapper.MealMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MealRepository @Inject constructor(private val remoteDataSource: RemoteDataSource,private val localDataSource: LocalDataSource):IMealRepository {
    override fun getAllMealsByFirstLetter(firstLetter: String): Flow<Resource<List<Meal>>> {
        return object : NetworkAndFetch<List<Meal>,ListMealResponse>(){
            override suspend fun createCall(): Flow<ApiResponse<ListMealResponse>> {
                return remoteDataSource.getAllMealsByFirstLetter(firstLetter)
            }

            override fun mapResponse(data: ListMealResponse): List<Meal> {
                return MealMapper.mapListMealResponseToListMeal(data)
            }

        }.asFlow()
    }

    override fun getMealDetail(id: Int): Flow<Resource<Meal>> {
        return object : NetworkAndFetch<Meal,ListMealResponse>(){
            override suspend fun createCall(): Flow<ApiResponse<ListMealResponse>> {
                return remoteDataSource.getMealDetail(id)
            }

            override fun mapResponse(data: ListMealResponse): Meal {
                return MealMapper.mapListMealResponseToListMeal(data)[0]
            }

        }.asFlow()
    }

    override fun searchMeal(name: String): Flow<Resource<List<Meal>>> {
        return object : NetworkAndFetch<List<Meal>,ListMealResponse>(){
            override suspend fun createCall(): Flow<ApiResponse<ListMealResponse>> {
                return remoteDataSource.searchMeal(name)
            }

            override fun mapResponse(data: ListMealResponse): List<Meal> {
                return MealMapper.mapListMealResponseToListMeal(data)
            }

        }.asFlow()
    }


    override suspend fun setFavoriteMeal(meal: Meal, status: Boolean) {
        CoroutineScope(IO).launch{
            localDataSource.setFavoriteMeal(meal.idMeal,status)
        }
    }

    override suspend fun insertFavoriteMeal(meal: Meal) {
        CoroutineScope(IO).launch{
            val mealEntity=MealMapper.mapModelToEntity(meal)
            mealEntity.isFavorite=true
            localDataSource.insertMeal(mealEntity)
        }
    }

    override suspend fun getFavoriteMealById(id: Int): Meal? {
        return  localDataSource.getFavoriteMealById(id)?.let {
            MealMapper.mapEntityToModel(it)
        }
    }

    override fun getFavoriteMeals(): LiveData<List<Meal>> {
        return Transformations.map(localDataSource.getFavoriteMeals()) {
            MealMapper.mapEntitiesToModels(it)
        }
    }
}