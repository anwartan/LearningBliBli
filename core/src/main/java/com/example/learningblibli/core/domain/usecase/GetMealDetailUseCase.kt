package com.example.learningblibli.core.domain.usecase

import com.example.learningblibli.core.data.repository.MealRepository
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.core.domain.model.Meal
import io.reactivex.Observable
import javax.inject.Inject


class GetMealDetailUseCase  @Inject constructor (private val mealRepository: MealRepository) {
    operator fun invoke(id:Int): Observable<Resource<Meal>> = mealRepository.getMealDetail(id)
}