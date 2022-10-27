package com.example.learningblibli.ui.search

import androidx.lifecycle.*
import com.example.learningblibli.core.base.BaseViewModel
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.core.domain.model.Meal
import com.example.learningblibli.core.domain.usecase.SearchMealUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor (private val searchMealUseCase: SearchMealUseCase) : BaseViewModel() {

    private val _meals = MutableLiveData<Resource<List<Meal>>>()
    val meals: LiveData<Resource<List<Meal>>> = _meals

    fun searchMeals(name:String){
        val result = searchMealUseCase(name)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                _meals.postValue(it)
            }
        addDisposable(result)
    }
}