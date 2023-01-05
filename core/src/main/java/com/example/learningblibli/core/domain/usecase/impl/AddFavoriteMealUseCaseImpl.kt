package com.example.learningblibli.core.domain.usecase.impl

import com.example.learningblibli.core.data.repository.MealRepository
import com.example.learningblibli.core.domain.usecase.contract.AddFavoriteMealUseCase
import com.example.learningblibli.core.utils.mapper.MealMapper
import com.example.learningblibli.lib_model.model.Meal
import javax.inject.Inject

class AddFavoriteMealUseCaseImpl @Inject constructor (private val mealRepository: MealRepository):AddFavoriteMealUseCase {
    override suspend operator fun invoke(meal: Meal){
        val mealEntity= MealMapper.mapModelToEntity(meal)
        mealEntity.isFavorite=true
        mealRepository.insertFavoriteMeal(mealEntity)
    }

}