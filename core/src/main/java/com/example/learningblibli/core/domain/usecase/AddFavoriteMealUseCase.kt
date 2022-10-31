package com.example.learningblibli.core.domain.usecase

import com.example.learningblibli.core.data.repository.MealRepository
import com.example.learningblibli.lib_model.model.Meal
import javax.inject.Inject

class AddFavoriteMealUseCase @Inject constructor (private val mealRepository: MealRepository) {
    suspend operator fun invoke(meal: Meal)= mealRepository.insertFavoriteMeal(meal)

}