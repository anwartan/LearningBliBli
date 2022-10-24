package com.example.learningblibli.di

import com.example.learningblibli.data.source.local.LocalDataSource
import com.example.learningblibli.data.source.local.room.MealDao
import com.example.learningblibli.data.source.remote.RemoteDataSource
import com.example.learningblibli.data.source.remote.network.ApiService
import dagger.Module
import dagger.Provides

@Module(includes = [RepositoryModule::class])
class AppModule {



}