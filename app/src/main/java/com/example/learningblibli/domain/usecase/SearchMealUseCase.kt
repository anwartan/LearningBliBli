package com.example.learningblibli.domain.usecase

import com.example.learningblibli.data.repository.MealRepository
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.domain.model.Meal
import kotlinx.coroutines.flow.Flow

class SearchMealUseCase(private val mealRepository: MealRepository) {
    operator fun invoke(name:String): Flow<Resource<List<Meal>>> = mealRepository.searchMeal(name)
}