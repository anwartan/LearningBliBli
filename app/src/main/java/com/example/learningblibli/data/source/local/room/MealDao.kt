package com.example.learningblibli.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.learningblibli.data.source.local.entity.MealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    @Query("SELECT * FROM meal where isFavorite=1")
    fun getFavoriteMeals(): LiveData<List<MealEntity>>

    @Query("SELECT * FROM meal where idMeal=:id")
    suspend fun getMealById(id:Int): MealEntity?

    @Query("UPDATE meal set isFavorite=:isFavorite where idMeal =:id ")
    suspend fun setFavoriteById(id:Int,isFavorite:Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(mealEntity: MealEntity)
}