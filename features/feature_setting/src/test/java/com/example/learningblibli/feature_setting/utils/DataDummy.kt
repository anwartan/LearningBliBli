package com.example.learningblibli.feature_setting.utils

import com.example.learningblibli.core.data.source.local.entity.MealEntity
import com.example.learningblibli.lib_model.model.Meal
import com.example.learningblibli.core.utils.mapper.MealMapper
import com.example.learningblibli.lib_model.model.User
import com.example.learningblibli.lib_model.response.ListMealResponse
import com.example.learningblibli.lib_model.response.MealResponse

object DataDummy {

    fun generateUser(): User {
        return User(
            uid = "1",
            email = "admin@gmail.com",
            displayName = "admin"
        )
    }
    private fun generateMealEntity(): MealEntity {
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
    private fun generateDummyMealResponses(): List<MealResponse> {
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