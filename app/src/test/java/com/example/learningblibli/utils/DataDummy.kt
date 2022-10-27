package com.example.learningblibli.utils

import com.example.learningblibli.core.data.source.local.entity.MealEntity
import com.example.learningblibli.core.domain.model.Meal
import com.example.learningblibli.core.utils.mapper.MealMapper

object DataDummy {
    fun generateMealEntity(): MealEntity {
        return MealEntity(
            1,
            strMealThumb = "https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg",
            strMeal = "meal1",
            strArea = "medan",
            strCategory = "category",
            isFavorite = true
        )
    }
    fun generateDummyMeal(): Meal {
        return MealMapper.mapEntityToModel(generateMealEntity())
    }
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
    private fun generateDummyMealResponses(): List<com.example.learningblibli.lib_model.MealResponse> {
        val newsList = ArrayList<com.example.learningblibli.lib_model.MealResponse>()
        for (i in 0..10) {
            val news = com.example.learningblibli.lib_model.MealResponse(
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

    fun generateDummyListMealResponse(): com.example.learningblibli.lib_model.ListMealResponse {
        return com.example.learningblibli.lib_model.ListMealResponse(generateDummyMealResponses())
    }

}