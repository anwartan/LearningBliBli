package com.example.learningblibli.core.domain.usecase

import com.example.learningblibli.core.data.repository.MealRepository
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.core.utils.mapper.MealMapper
import com.example.learningblibli.lib_model.model.Meal
import io.reactivex.Observable
import javax.inject.Inject

class SearchMealUseCase @Inject constructor(private val mealRepository: MealRepository) {
    operator fun invoke(name: String): Observable<Resource<List<Meal>>> {
        return mealRepository.searchMeal(name).map {
            if (it.meals == null || it.meals.orEmpty().isEmpty()) {
                Resource.Error("EMPTY")
            } else {
                Resource.Success(MealMapper.mapListMealResponseToListMeal(it))
            }
        }.onErrorReturn {
            Resource.Error(it.message ?: "SERVER ERROR")
        }
    }
}