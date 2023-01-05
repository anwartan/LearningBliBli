package com.example.learningblibli.core.domain.usecase.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.learningblibli.core.data.repository.MealRepository
import com.example.learningblibli.core.domain.usecase.contract.GetFavoriteMealUseCase
import com.example.learningblibli.core.utils.mapper.MealMapper
import com.example.learningblibli.lib_model.model.Meal
import javax.inject.Inject

class GetFavoriteMealUsecaseImpl  @Inject constructor (private val mealRepository: MealRepository) : GetFavoriteMealUseCase{
    override operator fun invoke(): LiveData<List<Meal>> =Transformations.map(mealRepository.getFavoriteMeals()){
        MealMapper.mapEntitiesToModels(it)
    }

}