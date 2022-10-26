package com.example.learningblibli.di

import com.example.learningblibli.data.repository.AuthRepository
import com.example.learningblibli.data.repository.MealRepository
import com.example.learningblibli.data.source.local.room.MealDao
import com.example.learningblibli.lib_api.service.ApiService
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class,DatabaseModule::class])
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