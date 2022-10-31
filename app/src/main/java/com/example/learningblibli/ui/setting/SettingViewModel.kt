package com.example.learningblibli.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningblibli.core.data.sharedPreferences.AppSharedPreferences
import com.example.learningblibli.core.utils.Constants
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingViewModel @Inject constructor(
    private val appSharedPreferences: AppSharedPreferences
) : ViewModel() {
    fun getThemeSettings(): LiveData<Boolean> {
        return appSharedPreferences.getBooleanAsLiveData(Constants.DARK_MODE)
    }
    fun saveThemeSetting(isActive:Boolean) {
        viewModelScope.launch {
            appSharedPreferences.putBoolean(Constants.DARK_MODE,isActive)

        }
    }
}