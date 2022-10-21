package com.example.learningblibli.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class MealEntity(
	val idMeal: Int,
	val strCategory: String? = null,
	val strMealThumb: String? = null,
	val strArea: String? = null,
	val strMeal: String? = null,
	var isFavorite:Boolean = false,
):Parcelable

