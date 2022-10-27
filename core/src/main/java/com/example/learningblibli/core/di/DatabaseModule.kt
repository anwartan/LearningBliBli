package com.example.learningblibli.core.di

import android.content.Context
import androidx.room.Room
import com.example.learningblibli.core.data.source.local.room.MealDao
import com.example.learningblibli.core.data.source.local.room.MealDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @Provides
    fun provideMealDatabase(context: Context): MealDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MealDatabase::class.java,
            "meal.db")
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    fun provideMealDao(database: MealDatabase): MealDao {
        return database.mealDao()
    }


}