package com.example.learningblibli.di

import com.example.learningblibli.data.repository.MealRepository
import com.example.learningblibli.data.repository.AuthRepository
import com.example.learningblibli.data.source.local.LocalDataSource
import com.example.learningblibli.data.source.remote.RemoteDataSource
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class,DatabaseModule::class])
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMealRepository(remoteDataSource: RemoteDataSource,localDataSource: LocalDataSource): MealRepository {
        return MealRepository(remoteDataSource, localDataSource)
    }
    @Singleton
    @Provides
    fun provideUserRepository(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepository(firebaseAuth)
    }

}