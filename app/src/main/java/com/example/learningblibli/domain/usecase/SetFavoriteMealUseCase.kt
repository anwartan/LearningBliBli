package com.example.learningblibli.domain.usecase

import com.example.learningblibli.data.repository.MealRepository
import com.example.learningblibli.domain.model.Meal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetFavoriteMealUseCase  @Inject constructor (private val mealRepository: MealRepository) {
    suspend operator fun invoke(meal:Meal, newStatus:Boolean)= mealRepository.setFavoriteMeal(meal,newStatus)

}