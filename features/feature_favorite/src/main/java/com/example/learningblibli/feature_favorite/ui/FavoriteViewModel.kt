package com.example.learningblibli.feature_favorite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.learningblibli.lib_model.model.Meal
import com.example.learningblibli.core.domain.usecase.GetFavoriteMealUsecase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val getFavoriteMealUsecase: GetFavoriteMealUsecase) : ViewModel() {
    private var _meals = MutableLiveData<List<Meal>>()
    val meals :LiveData<List<Meal>> get() = _meals

    fun getMeals(){
        _meals = getFavoriteMealUsecase() as MutableLiveData<List<Meal>>
    }
}