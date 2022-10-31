package com.example.learningblibli.core.domain.usecase

import com.example.learningblibli.core.data.repository.MealRepository
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.lib_model.model.Meal
import io.reactivex.Observable
import javax.inject.Inject

class GetMealsByFirstNameUseCase  @Inject constructor (private val mealRepository: MealRepository) {
    operator fun invoke(firstName:String):Observable<Resource<List<Meal>>> = mealRepository.getAllMealsByFirstLetter(firstName)
}