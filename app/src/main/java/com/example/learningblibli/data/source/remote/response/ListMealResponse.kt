package com.example.learningblibli.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMealResponse (
    @field:SerializedName("meals")
    val meals: List<MealResponse>? = null
)