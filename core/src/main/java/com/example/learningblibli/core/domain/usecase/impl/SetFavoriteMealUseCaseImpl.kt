package com.example.learningblibli.core.domain.usecase.impl

import com.example.learningblibli.core.data.repository.MealRepository
import com.example.learningblibli.core.domain.usecase.contract.SetFavoriteMealUseCase
import com.example.learningblibli.core.utils.mapper.MealMapper
import com.example.learningblibli.lib_model.model.Meal
import javax.inject.Inject

class SetFavoriteMealUseCaseImpl  @Inject constructor (private val mealRepository: MealRepository):SetFavoriteMealUseCase {
    override suspend operator fun invoke(meal: Meal, newStatus:Boolean){
        val mealEntity = MealMapper.mapModelToEntity(meal)
        mealRepository.setFavoriteMeal(mealEntity,newStatus)
    }

}