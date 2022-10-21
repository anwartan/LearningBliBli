package com.example.learningblibli.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "meal")
@Parcelize
data class MealEntity(
	@PrimaryKey(autoGenerate = true)
	val idMeal: Int,
	val strCategory: String? = null,
	val strMealThumb: String? = null,
	val strArea: String? = null,
	val strMeal: String? = null,
	var isFavorite:Boolean = false,
):Parcelable

