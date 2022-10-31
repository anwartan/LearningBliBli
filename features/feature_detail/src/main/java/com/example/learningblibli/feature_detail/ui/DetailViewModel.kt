package com.example.learningblibli.feature_detail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.learningblibli.core.base.BaseViewModel
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.core.domain.usecase.AddFavoriteMealUseCase
import com.example.learningblibli.core.domain.usecase.GetFavoriteMealByIdUsecase
import com.example.learningblibli.core.domain.usecase.GetMealDetailUseCase
import com.example.learningblibli.core.domain.usecase.SetFavoriteMealUseCase
import com.example.learningblibli.lib_model.model.Meal
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getMealDetailUseCase: GetMealDetailUseCase,
    private val setFavoriteMealUseCase: SetFavoriteMealUseCase,
    private val addFavoriteMealUseCase: AddFavoriteMealUseCase,
    private val getFavoriteMealByIdUsecase: GetFavoriteMealByIdUsecase
    ) : BaseViewModel() {

    private val _detailMeal = MutableLiveData<Resource<Meal>>()
    val detailMeal: LiveData<Resource<Meal>> get()=_detailMeal

    private val _isFavorite = MutableLiveData(false)
    val isFavorite: LiveData<Boolean> get() = _isFavorite


    fun getDetailMeal(meal: Meal){
        val result = getMealDetailUseCase(meal.idMeal)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {response->
                _detailMeal.postValue(response)
            }
        addDisposable(result)
    }

    fun getFavoriteMeal(idMeal:Int){
        viewModelScope.launch{
            val mealFavorite = getFavoriteMealByIdUsecase(idMeal)
            if(mealFavorite==null){
                _isFavorite.postValue(false)
            }else{
                _isFavorite.postValue(mealFavorite.isFavorite)
            }
        }

    }

    fun setFavoriteMeal(meal: Meal){
        viewModelScope.launch {
            val mealFavorite = getFavoriteMealByIdUsecase(meal.idMeal)
            if(mealFavorite==null){
                addFavoriteMealUseCase(meal)
            }else{
                setFavoriteMealUseCase(meal,!mealFavorite.isFavorite)
            }
            getFavoriteMeal(meal.idMeal)
        }
    }
}