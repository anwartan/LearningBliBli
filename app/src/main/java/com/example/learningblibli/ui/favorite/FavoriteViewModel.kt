package com.example.learningblibli.ui.favorite

import androidx.lifecycle.ViewModel
import com.example.learningblibli.domain.usecase.GetFavoriteMealUsecase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(getFavoriteMealUsecase: GetFavoriteMealUsecase) : ViewModel() {
    val meals = getFavoriteMealUsecase()
}