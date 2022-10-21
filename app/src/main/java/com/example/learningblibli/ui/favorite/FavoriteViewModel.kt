package com.example.learningblibli.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.learningblibli.domain.usecase.GetFavoriteMealUsecase

class FavoriteViewModel(getFavoriteMealUsecase: GetFavoriteMealUsecase) : ViewModel() {
    val meals = getFavoriteMealUsecase().asLiveData()
}