package com.example.learningblibli.core.domain.usecase.contract

import androidx.lifecycle.LiveData
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.lib_model.model.Meal
import io.reactivex.Observable

interface GetMealDetailUseCase {
    operator fun invoke(id:Int): Observable<Resource<Meal>>
}