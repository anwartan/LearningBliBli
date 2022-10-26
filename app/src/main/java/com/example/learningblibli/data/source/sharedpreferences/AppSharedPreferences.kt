package com.example.learningblibli.data.source.sharedpreferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.learningblibli.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.SHARED_PREFERENCES)

class AppSharedPreferences @Inject constructor(private val context: Context) :
    IAppSharedPreferences {
    override fun getBoolean(key: String, defaultValue: Boolean): Flow<Boolean> {
       return context.dataStore.data.map {
           it[booleanPreferencesKey(key)]?:defaultValue
       }
    }

    override fun getBooleanAsLiveData(key: String, defaultValue: Boolean): LiveData<Boolean> {
        return context.dataStore.data.map {
            it[booleanPreferencesKey(key)]?:defaultValue
        }.asLiveData()
    }

    override suspend fun putBoolean(key: String, value: Boolean) {
        context.dataStore.edit {
            it[booleanPreferencesKey(key)] = value
        }
    }
}