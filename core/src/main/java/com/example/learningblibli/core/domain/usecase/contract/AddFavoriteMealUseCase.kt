package com.example.learningblibli.core.domain.usecase.contract

import com.example.learningblibli.lib_model.model.Meal

interface AddFavoriteMealUseCase {
    suspend operator fun invoke(meal: Meal)
}