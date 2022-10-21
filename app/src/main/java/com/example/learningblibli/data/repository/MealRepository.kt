package com.example.learningblibli.data.repository

import com.example.learningblibli.data.NetworkAndFetch
import com.example.learningblibli.data.source.local.LocalDataSource
import com.example.learningblibli.data.source.remote.RemoteDataSource
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.data.source.remote.network.ApiResponse
import com.example.learningblibli.data.source.remote.response.ListMealResponse
import com.example.learningblibli.domain.model.Meal
import com.example.learningblibli.domain.repository.IMealRepository
import com.example.learningblibli.utils.mapper.MealMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class MealRepository(private val remoteDataSource: RemoteDataSource,private val localDataSource: LocalDataSource):IMealRepository {
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


    override fun setFavoriteMeal(meal: Meal, status: Boolean):Flow<Boolean>  = flow{
        val mealEntities = localDataSource.getMealById(meal.idMeal).first()
        if(mealEntities==null){
            val newMealEntity = MealMapper.mapModelToEntity(meal)
            newMealEntity.isFavorite=status
            localDataSource.insertMeal(newMealEntity)
        }else{
            if(mealEntities.isFavorite==status){
                emit(false)
            }
            localDataSource.setFavoriteMeal(mealEntities.idMeal,status)
        }
        val newMealEntity = MealMapper.mapModelToEntity(meal)
            newMealEntity.isFavorite=status
        localDataSource.insertMeal(newMealEntity)
    }

    override fun getFavoriteMeals(): Flow<List<Meal>> = flow {
        emit(arrayListOf())
    }
}