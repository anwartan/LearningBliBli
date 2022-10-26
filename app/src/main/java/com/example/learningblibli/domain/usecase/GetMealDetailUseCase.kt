package com.example.learningblibli.domain.usecase

import com.example.learningblibli.data.repository.MealRepository
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.domain.model.Meal
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetMealDetailUseCase  @Inject constructor (private val mealRepository: MealRepository) {
    operator fun invoke(id:Int): Observable<Resource<Meal>> = mealRepository.getMealDetail(id)
}