package com.example.learningblibli.data.source.sharedpreferences

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow


interface IAppSharedPreferences {
    fun getBoolean(key: String,defaultValue:Boolean = false): Flow<Boolean>
    fun getBooleanAsLiveData(key: String,defaultValue:Boolean = false):LiveData<Boolean>
    suspend fun putBoolean(key: String,value:Boolean)
}