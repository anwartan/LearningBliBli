package com.example.learningblibli.core.domain.usecase.contract

import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.lib_model.model.Meal
import io.reactivex.Observable

interface SearchMealUseCase {
    operator fun invoke(name: String): Observable<Resource<List<Meal>>>
}