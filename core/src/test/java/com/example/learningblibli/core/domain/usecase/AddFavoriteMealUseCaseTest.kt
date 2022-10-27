package com.example.learningblibli.core.domain.usecase

import com.example.learningblibli.core.domain.usecase.AddFavoriteMealUseCase
import com.example.learningblibli.core.data.repository.MealRepository
import com.example.learningblibli.core.utils.DataDummy
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class AddFavoriteMealUseCaseTest{
    @Mock
    private lateinit var mealRepository: MealRepository

    @InjectMocks
    private lateinit var addFavoriteMealUseCase: AddFavoriteMealUseCase
    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)

    }

    @After
    fun tearDown(){
        Mockito.verifyNoMoreInteractions(mealRepository)
    }

    @Test
    fun addFavoriteMeal() = runBlocking{
        val meal = DataDummy.generateDummyMeal()
        addFavoriteMealUseCase(meal)
        Mockito.verify(mealRepository).insertFavoriteMeal(meal)
    }

}