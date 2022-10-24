package com.example.learningblibli.di

import android.content.Context
import androidx.room.Room
import com.example.learningblibli.data.source.local.LocalDataSource
import com.example.learningblibli.data.source.local.room.MealDao
import com.example.learningblibli.data.source.local.room.MealDatabase
import com.example.learningblibli.data.source.sharedpreferences.AppSharedPreferences
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @Provides
    fun provideMealDatabase(context: Context):MealDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            MealDatabase::class.java,
            "meal.db")
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    fun provideMealDao(database: MealDatabase):MealDao{
        return database.mealDao()
    }
    @Provides
    fun provideLocalDataSource(mealDao: MealDao): LocalDataSource {
        return LocalDataSource(mealDao)
    }
    @Provides
    fun provideSharedPreferences(context: Context):AppSharedPreferences{
        return AppSharedPreferences(context)
    }
}