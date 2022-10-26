package com.example.learningblibli.utils.mapper

import com.example.learningblibli.data.source.local.entity.MealEntity
import com.example.learningblibli.lib_model.ListMealResponse
import com.example.learningblibli.lib_model.MealResponse
import com.example.learningblibli.domain.model.Meal

object MealMapper {
    private fun mapResponseToModel(it: com.example.learningblibli.lib_model.MealResponse): Meal {
        return Meal(
            idMeal = it.idMeal,
            dateModified = it.dateModified,
            strArea = it.strArea,
            strCategory = it.strCategory,
            strCreativeCommonsConfirmed = it.strCreativeCommonsConfirmed,
            strDrinkAlternate = it.strDrinkAlternate,
            strImageSource = it.strImageSource,
            strIngredient1 = it.strIngredient1,
            strIngredient2 = it.strIngredient2,
            strIngredient3 = it.strIngredient3,
            strIngredient4 = it.strIngredient4,
            strIngredient5 = it.strIngredient5,
            strIngredient6 = it.strIngredient6,
            strIngredient7 = it.strIngredient7,
            strIngredient8 = it.strIngredient8,
            strIngredient9 = it.strIngredient9,
            strIngredient10 = it.strIngredient10,
            strIngredient11 = it.strIngredient11,
            strIngredient12 = it.strIngredient12,
            strIngredient13 = it.strIngredient13,
            strIngredient14 = it.strIngredient14,
            strIngredient15 = it.strIngredient15,
            strIngredient16 = it.strIngredient16,
            strIngredient17 = it.strIngredient17,
            strIngredient18 = it.strIngredient18,
            strIngredient19 = it.strIngredient19,
            strIngredient20 = it.strIngredient20,
            strInstructions = it.strInstructions,
            strMeal = it.strMeal,
            strMealThumb = it.strMealThumb,
            strSource = it.strSource,
            strTags = it.strTags,
            strYoutube = it.strYoutube,
            strMeasure1 = it.strMeasure1,
            strMeasure2 = it.strMeasure2,
            strMeasure3 = it.strMeasure3,
            strMeasure4 = it.strMeasure4,
            strMeasure5 = it.strMeasure5,
            strMeasure6 = it.strMeasure6,
            strMeasure7 = it.strMeasure7,
            strMeasure8 = it.strMeasure8,
            strMeasure9 = it.strMeasure9,
            strMeasure10 = it.strMeasure10,
            strMeasure11= it.strMeasure11,
            strMeasure12 = it.strMeasure12,
            strMeasure13 = it.strMeasure13,
            strMeasure14 = it.strMeasure14,
            strMeasure15 = it.strMeasure15,
            strMeasure16 = it.strMeasure16,
            strMeasure17 = it.strMeasure17,
            strMeasure18 = it.strMeasure18,
            strMeasure19 = it.strMeasure19,
            strMeasure20 = it.strMeasure20,
            isFavorite = false
            )
    }
    fun mapListMealResponseToListMeal(listMealResponse: com.example.learningblibli.lib_model.ListMealResponse):List<Meal>{
        val meals = arrayListOf<Meal>()
        listMealResponse.meals?.map {
            val meal = mapResponseToModel(it)
            meals.add(meal)
        }
        return meals
    }
    fun mapModelToEntity(meal: Meal):MealEntity{
        return MealEntity(
            idMeal = meal.idMeal,
            strCategory = meal.strCategory,
            strMealThumb = meal.strMealThumb,
            strMeal = meal.strMeal,
            strArea = meal.strArea,
            isFavorite = meal.isFavorite
        )
    }
    fun mapEntityToModel(mealEntity: MealEntity):Meal{
        return Meal(
            idMeal = mealEntity.idMeal,
            strCategory = mealEntity.strCategory,
            strMealThumb = mealEntity.strMealThumb,
            strMeal = mealEntity.strMeal,
            strArea = mealEntity.strArea,
            isFavorite = mealEntity.isFavorite

        )
    }
    fun mapEntitiesToModels(mealEntities:List<MealEntity>):List<Meal>{
        val meals = mealEntities.map {
            mapEntityToModel(it)
        }
        return meals
    }
}