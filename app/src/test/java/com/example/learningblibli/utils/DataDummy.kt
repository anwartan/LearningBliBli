package com.example.learningblibli.utils

import com.example.learningblibli.data.source.remote.response.ListMealResponse
import com.example.learningblibli.data.source.remote.response.MealResponse
import com.example.learningblibli.domain.model.Meal

object DataDummy {
    fun generateDummyMeals(): List<Meal> {
        val newsList = ArrayList<Meal>()
        for (i in 0..10) {
            val news = Meal(
                i,
                strImageSource = "https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg",
                strMeal = "meal1",
                strArea = "medan",
                strCategory = "category"
            )
            newsList.add(news)
        }
        return newsList
    }
    fun generateDummyMealResponses(): List<MealResponse> {
        val newsList = ArrayList<MealResponse>()
        for (i in 0..10) {
            val news = MealResponse(
                i,
                strImageSource = "https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg",
                strMeal = "meal1",
                strArea = "medan",
                strCategory = "category"
            )
            newsList.add(news)
        }
        return newsList
    }

    fun generateDummyListMealResponse(): ListMealResponse {
        return ListMealResponse(generateDummyMealResponses())
    }

}