package com.example.learningblibli.ui.search

import androidx.lifecycle.*
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.domain.model.Meal
import com.example.learningblibli.domain.usecase.SearchMealUseCase

class SearchViewModel(searchMealUseCase: SearchMealUseCase) : ViewModel() {
    private val search = MutableLiveData<String>()

    val meals: LiveData<Resource<List<Meal>>> = Transformations.switchMap(search) {
        searchMealUseCase(it).asLiveData()
    }
    fun setSearch(name:String){
        search.postValue(name)
    }
}