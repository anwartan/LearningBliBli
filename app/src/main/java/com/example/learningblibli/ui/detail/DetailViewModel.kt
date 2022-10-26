package com.example.learningblibli.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.learningblibli.base.BaseViewModel
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.domain.model.Meal
import com.example.learningblibli.domain.usecase.AddFavoriteMealUseCase
import com.example.learningblibli.domain.usecase.GetFavoriteMealByIdUsecase
import com.example.learningblibli.domain.usecase.GetMealDetailUseCase
import com.example.learningblibli.domain.usecase.SetFavoriteMealUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
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

    fun getDetailMeal(meal: Meal){
        val result = getMealDetailUseCase(meal.idMeal)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {response->
                _detailMeal.postValue(response)
            }
        addDisposable(result)
    }

    fun setFavoriteMovie(meal: Meal){
        viewModelScope.launch (Dispatchers.IO){
            val mealFavorite = getFavoriteMealByIdUsecase(meal.idMeal)
            if(mealFavorite==null){
                addFavoriteMealUseCase(meal)
            }else{
                setFavoriteMealUseCase(meal,!mealFavorite.isFavorite)
            }
        }
    }




}