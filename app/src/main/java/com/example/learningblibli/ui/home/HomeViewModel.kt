package com.example.learningblibli.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.learningblibli.core.base.BaseViewModel
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.core.data.sharedPreferences.AppSharedPreferences
import com.example.learningblibli.core.domain.model.Meal
import com.example.learningblibli.core.domain.usecase.GetMealsByFirstNameUseCase
import com.example.learningblibli.core.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor (
    private val getMealsByFirstNameUseCase: GetMealsByFirstNameUseCase
) : BaseViewModel() {

    @Inject
    lateinit var appSharedPreferences: AppSharedPreferences



    private val _meals =MutableLiveData<Resource<List<Meal>>>()
    val meals :LiveData<Resource<List<Meal>>> get() = _meals
    fun getMeals(){
        val result = getMealsByFirstNameUseCase("a")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _meals.postValue(it)
            }
        addDisposable(result)
    }

    fun getThemeSettings(): LiveData<Boolean> {
        return appSharedPreferences.getBooleanAsLiveData(Constants.DARK_MODE)
    }
    fun saveThemeSetting(isActive:Boolean) {
        viewModelScope.launch {
            appSharedPreferences.putBoolean(Constants.DARK_MODE,isActive)

        }
    }

}