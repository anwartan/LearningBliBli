package com.example.learningblibli.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.learningblibli.domain.usecase.GetMealsByFirstNameUseCase

class HomeViewModel(getMealsByFirstNameUseCase:GetMealsByFirstNameUseCase) : ViewModel() {
    val meals = getMealsByFirstNameUseCase("a").asLiveData()
}