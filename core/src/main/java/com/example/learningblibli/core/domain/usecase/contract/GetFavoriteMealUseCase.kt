package com.example.learningblibli.core.domain.usecase.contract

import androidx.lifecycle.LiveData
import com.example.learningblibli.lib_model.model.Meal

interface GetFavoriteMealUseCase {
    operator fun invoke(): LiveData<List<Meal>>
}