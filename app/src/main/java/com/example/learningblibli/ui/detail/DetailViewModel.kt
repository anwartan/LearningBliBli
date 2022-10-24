package com.example.learningblibli.ui.detail

import androidx.lifecycle.*
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.domain.model.Meal
import com.example.learningblibli.domain.usecase.AddFavoriteMealUseCase
import com.example.learningblibli.domain.usecase.GetFavoriteMealByIdUsecase
import com.example.learningblibli.domain.usecase.GetMealDetailUseCase
import com.example.learningblibli.domain.usecase.SetFavoriteMealUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    getMealDetailUseCase: GetMealDetailUseCase,
    private val setFavoriteMealUseCase: SetFavoriteMealUseCase,
    private val addFavoriteMealUseCase: AddFavoriteMealUseCase,
    private val getFavoriteMealByIdUsecase: GetFavoriteMealByIdUsecase
    ) : ViewModel() {

    private val meal = MutableLiveData<Meal>()

    val detailMovie: LiveData<Resource<Meal>> = Transformations.switchMap(meal) {
        getMealDetailUseCase(it.idMeal).asLiveData()
    }

    fun setFavoriteMovie(){
        meal.value?.let {

            viewModelScope.launch (Dispatchers.IO){
                val mealFavorite = getFavoriteMealByIdUsecase(it.idMeal)
                if(mealFavorite==null){
                    addFavoriteMealUseCase(it)
                }else{
                    setFavoriteMealUseCase(it,!mealFavorite.isFavorite)
                }

            }
        }
    }

    fun setMeal(newMeal:Meal){
        meal.postValue(newMeal)
    }
}