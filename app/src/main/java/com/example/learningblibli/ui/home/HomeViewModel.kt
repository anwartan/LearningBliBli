package com.example.learningblibli.ui.home

import androidx.lifecycle.*
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.data.source.sharedpreferences.AppSharedPreferences
import com.example.learningblibli.domain.model.Meal
import com.example.learningblibli.domain.usecase.GetCurrentUserUseCase
import com.example.learningblibli.domain.usecase.GetMealsByFirstNameUseCase
import com.example.learningblibli.domain.usecase.LogoutByFirebaseUseCase
import com.example.learningblibli.utils.Constants
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor (private val getMealsByFirstNameUseCase:GetMealsByFirstNameUseCase,private val logoutByFirebaseUseCase: LogoutByFirebaseUseCase,private val getCurrentUserUseCase : GetCurrentUserUseCase) : ViewModel() {

    @Inject
    lateinit var appSharedPreferences: AppSharedPreferences

    private var _meals = MutableLiveData<Resource<List<Meal>>>()
    val meals:LiveData<Resource<List<Meal>>>
            get() =_meals
    val currentUser: LiveData<FirebaseUser?> get() = getCurrentUserUseCase()


    fun getThemeSettings(): LiveData<Boolean> {
        return appSharedPreferences.getBooleanAsLiveData(Constants.DARK_MODE)
    }
    fun saveThemeSetting(isActive:Boolean) {
        viewModelScope.launch {
            isActive.let {
                appSharedPreferences.putBoolean(Constants.DARK_MODE,isActive)
            }
        }
    }

    fun getMeals(){
        _meals= getMealsByFirstNameUseCase("a").asLiveData() as MutableLiveData<Resource<List<Meal>>>
    }

    fun logout(){
        logoutByFirebaseUseCase()
    }

}