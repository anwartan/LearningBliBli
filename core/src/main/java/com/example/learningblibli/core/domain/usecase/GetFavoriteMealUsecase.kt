package com.example.learningblibli.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.learningblibli.core.data.repository.MealRepository
import com.example.learningblibli.core.domain.model.Meal
import javax.inject.Inject

class GetFavoriteMealUsecase  @Inject constructor (private val mealRepository: MealRepository) {
    operator fun invoke(): LiveData<List<Meal>> = mealRepository.getFavoriteMeals()

}