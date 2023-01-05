package com.example.learningblibli.core.domain.usecase.impl

import com.example.learningblibli.core.data.repository.MealRepository
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.core.domain.usecase.contract.GetMealDetailUseCase
import com.example.learningblibli.core.utils.mapper.MealMapper
import com.example.learningblibli.lib_model.model.Meal
import io.reactivex.Observable
import javax.inject.Inject


class GetMealDetailUseCaseImpl  @Inject constructor (private val mealRepository: MealRepository):GetMealDetailUseCase {
    override operator fun invoke(id:Int): Observable<Resource<Meal>> {
        return mealRepository.getMealDetail(id) .map {
            if(it.meals==null ||  it.meals.orEmpty().isEmpty()){
                Resource.Error("EMPTY")
            }else{
                Resource.Success(MealMapper.mapListMealResponseToListMeal(it)[0])
            }
        }.onErrorReturn {
            Resource.Error(it.message?:"SERVER ERROR")
        }
    }
}