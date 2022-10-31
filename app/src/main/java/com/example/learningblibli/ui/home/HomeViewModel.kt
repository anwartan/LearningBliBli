package com.example.learningblibli.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.learningblibli.core.base.BaseViewModel
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.core.domain.usecase.GetMealsByFirstNameUseCase
import com.example.learningblibli.lib_model.model.Meal
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getMealsByFirstNameUseCase: GetMealsByFirstNameUseCase,
) : BaseViewModel() {

    private val _meals = MutableLiveData<List<Meal>>()
    val meals: LiveData<List<Meal>> get() = _meals

    private val _recommendationMeals = MutableLiveData<List<Meal>>()
    val recommendationMeal: LiveData<List<Meal>> get() = _recommendationMeals

    private val _newMeals = MutableLiveData<List<Meal>>()
    val newMeals: LiveData<List<Meal>> get() = _newMeals

    fun getMeals() {
        val result = getMealsByFirstNameUseCase("a")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is Resource.Success -> {
                        it.data?.let { meals ->
                            _meals.postValue(meals)
                        }
                    }
                    is Resource.Error -> {
                        showError(it.message ?: "Something wrong with server")
                    }
                    else -> {}
                }
            }
        addDisposable(result)
    }

    fun getRecommendationMeals() {
        val result = getMealsByFirstNameUseCase("b")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is Resource.Success -> {
                        it.data?.let { meals ->
                            _recommendationMeals.postValue(meals)
                        }
                    }
                    is Resource.Error -> {
                        showError(it.message ?: "Something wrong with server")
                    }
                    else -> {}
                }
            }
        addDisposable(result)
    }

    fun getNewMeals() {
        val result = getMealsByFirstNameUseCase("s")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is Resource.Success -> {
                        it.data?.let { meals ->
                            _newMeals.postValue(meals)
                        }
                    }
                    is Resource.Error -> {
                        showError(it.message ?: "Something wrong with server")
                    }
                    else -> {}
                }
            }
        addDisposable(result)
    }

}