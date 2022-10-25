package com.example.learningblibli.domain.usecase

import com.example.learningblibli.data.repository.MealRepository
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.domain.model.Meal
import io.reactivex.Observable
import javax.inject.Inject

class GetMealsByFirstNameUseCase  @Inject constructor (private val mealRepository: MealRepository) {
    operator fun invoke(firstName:String):Observable<Resource<List<Meal>>> = mealRepository.getAllMealsByFirstLetter(firstName)
}