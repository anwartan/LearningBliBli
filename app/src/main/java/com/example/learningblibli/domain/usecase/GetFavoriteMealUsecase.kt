package com.example.learningblibli.domain.usecase

import androidx.lifecycle.LiveData
import com.example.learningblibli.data.repository.MealRepository
import com.example.learningblibli.domain.model.Meal
import javax.inject.Inject

class GetFavoriteMealUsecase  @Inject constructor (private val mealRepository: MealRepository) {
    operator fun invoke(): LiveData<List<Meal>> = mealRepository.getFavoriteMeals()

}