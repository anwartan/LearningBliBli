package com.example.learningblibli.ui.detail

import androidx.lifecycle.*
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.domain.model.Meal
import com.example.learningblibli.domain.usecase.GetMealDetailUseCase
import com.example.learningblibli.domain.usecase.SetFavoriteMealUseCase

class DetailViewModel(getMealDetailUseCase: GetMealDetailUseCase,private val setFavoriteMealUseCase: SetFavoriteMealUseCase) : ViewModel() {

    private val meal = MutableLiveData<Meal>()

    val detailMovie: LiveData<Resource<Meal>> = Transformations.switchMap(meal) {
        getMealDetailUseCase(it.idMeal).asLiveData()
    }

    fun setFavoriteMovie(newStatus:Boolean){
        meal.value?.let {
            setFavoriteMealUseCase(it,newStatus)
        }
    }

    fun setMeal(newMeal:Meal){
        meal.postValue(newMeal)
    }
}