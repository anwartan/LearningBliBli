package com.example.learningblibli.domain.usecase

import com.example.learningblibli.data.repository.MealRepository
import com.example.learningblibli.domain.model.Meal
import kotlinx.coroutines.flow.Flow

class SetFavoriteMealUseCase(private val mealRepository: MealRepository) {
    operator fun invoke(meal:Meal,newStatus:Boolean): Flow<Boolean> = mealRepository.setFavoriteMeal(meal,newStatus)

}