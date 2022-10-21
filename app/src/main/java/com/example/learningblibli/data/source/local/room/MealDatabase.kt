package com.example.learningblibli.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.learningblibli.data.source.local.entity.MealEntity


@Database(entities = [MealEntity::class], version = 1)
abstract class MealDatabase:RoomDatabase() {


    abstract fun mealDao():MealDao

    companion object {
        @Volatile
        private var INSTANCE: MealDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): MealDatabase {
            if (INSTANCE == null) {
                synchronized(MealDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        MealDatabase::class.java, "meal_db")
                        .build()
                }
            }
            return INSTANCE as MealDatabase
        }
    }
}