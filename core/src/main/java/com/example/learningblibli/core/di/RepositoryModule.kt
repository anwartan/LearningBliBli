package com.example.learningblibli.core.di

import com.example.learningblibli.core.data.source.local.room.MealDao
import com.example.learningblibli.core.data.repository.AuthRepository
import com.example.learningblibli.core.data.repository.MealRepository
import com.example.learningblibli.lib_api.service.ApiService
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, DatabaseModule::class])
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMealRepository(apiService: ApiService, mealDao: MealDao): MealRepository {
        return MealRepository(apiService, mealDao)
    }
    @Singleton
    @Provides
    fun provideUserRepository(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepository(firebaseAuth)
    }

}