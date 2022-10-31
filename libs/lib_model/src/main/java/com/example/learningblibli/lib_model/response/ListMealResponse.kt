package com.example.learningblibli.lib_model.response

import com.google.gson.annotations.SerializedName

data class ListMealResponse (
    @field:SerializedName("meals")
    val meals: List<MealResponse>? = null
)