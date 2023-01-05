package com.example.learningblibli.feature_favorite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.learningblibli.core.domain.usecase.contract.GetFavoriteMealUseCase
import com.example.learningblibli.lib_model.model.Meal
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val getFavoriteMealUsecase: GetFavoriteMealUseCase) : ViewModel() {
    private var _meals = MutableLiveData<List<Meal>>()
    val meals :LiveData<List<Meal>> get() = _meals

    fun getMeals(){
        _meals = getFavoriteMealUsecase() as MutableLiveData<List<Meal>>
    }
}