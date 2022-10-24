package com.example.learningblibli.domain.usecase

import androidx.lifecycle.LiveData
import com.example.learningblibli.data.repository.MealRepository
import com.example.learningblibli.domain.model.Meal
import javax.inject.Inject

class GetFavoriteMealByIdUsecase  @Inject constructor (private val mealRepository: MealRepository) {
    suspend operator fun invoke(id:Int): Meal? = mealRepository.getFavoriteMealById(id)

}