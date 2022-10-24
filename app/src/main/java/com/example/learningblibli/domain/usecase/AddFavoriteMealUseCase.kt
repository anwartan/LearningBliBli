package com.example.learningblibli.domain.usecase

import com.example.learningblibli.data.repository.MealRepository
import com.example.learningblibli.domain.model.Meal
import javax.inject.Inject

class AddFavoriteMealUseCase @Inject constructor (private val mealRepository: MealRepository) {
    suspend operator fun invoke(meal: Meal)= mealRepository.insertFavoriteMeal(meal)

}