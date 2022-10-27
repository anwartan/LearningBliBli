package com.example.learningblibli.core.di

import android.content.Context
import com.example.learningblibli.core.data.sharedPreferences.AppSharedPreferences
import dagger.Module
import dagger.Provides

@Module(includes = [RepositoryModule::class])
class CoreModule{
    @Provides
    fun provideSharedPreferences(context: Context): AppSharedPreferences {
        return AppSharedPreferences(context)
    }
}