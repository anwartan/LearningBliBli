package com.example.learningblibli.core.domain.usecase

import com.example.learningblibli.core.data.repository.MealRepository
import com.example.learningblibli.core.utils.mapper.MealMapper
import com.example.learningblibli.lib_model.model.Meal
import javax.inject.Inject

class GetFavoriteMealByIdUsecase  @Inject constructor (private val mealRepository: MealRepository) {
    suspend operator fun invoke(id:Int): Meal? =  mealRepository.getFavoriteMealById(id)?.let {
        MealMapper.mapEntityToModel(it)
    }

}