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
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MealRepository @Inject constructor(private val remoteDataSource: RemoteDataSource,private val localDataSource: LocalDataSource):IMealRepository {

    override fun getAllMealsByFirstLetter(firstLetter: String): Observable<Resource<List<Meal>>> {
        val result = PublishSubject.create<Resource<List<Meal>>>()
        val apiResponse = remoteDataSource.getAllMealsByFirstLetter(firstLetter)
        apiResponse
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                when(response){
                    is ApiResponse.Success->{
                        result.onNext(Resource.Success(MealMapper.mapListMealResponseToListMeal(response.data)))
                    }
                    is ApiResponse.Error ->{
                        result.onNext(Resource.Error(response.errorMessage))
                    }
                    is ApiResponse.Empty->{
                        result.onNext(Resource.Error("EMPTY"))
                    }
                }
            }
        return result


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