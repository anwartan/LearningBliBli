package com.example.learningblibli.core.domain.usecase.contract

import com.example.learningblibli.lib_model.model.Meal

interface GetFavoriteMealByIdUseCase {
    suspend operator fun invoke(id:Int): Meal?
}