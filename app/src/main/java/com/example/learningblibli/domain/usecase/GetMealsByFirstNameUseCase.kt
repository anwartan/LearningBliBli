package com.example.learningblibli.domain.usecase

import com.example.learningblibli.data.repository.MealRepository
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.domain.model.Meal
import kotlinx.coroutines.flow.Flow

class GetMealsByFirstNameUseCase(private val mealRepository: MealRepository) {
    operator fun invoke(firstName:String):Flow<Resource<List<Meal>>> = mealRepository.getAllMealsByFirstLetter(firstName)
}