package com.example.learningblibli.core.domain.usecase

import com.example.learningblibli.core.data.repository.MealRepository
import com.example.learningblibli.core.domain.model.Meal
import javax.inject.Inject

class GetFavoriteMealByIdUsecase  @Inject constructor (private val mealRepository: MealRepository) {
    suspend operator fun invoke(id:Int): Meal? = mealRepository.getFavoriteMealById(id)

}