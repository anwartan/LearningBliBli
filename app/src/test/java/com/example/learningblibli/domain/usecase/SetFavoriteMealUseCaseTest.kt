package com.example.learningblibli.domain.usecase

import com.example.learningblibli.data.repository.MealRepository
import com.example.learningblibli.utils.DataDummy
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class SetFavoriteMealUseCaseTest{
    @Mock
    private lateinit var mealRepository: MealRepository

    @InjectMocks
    private lateinit var setFavoriteMealUseCase: SetFavoriteMealUseCase

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
    }

    @After
    fun tearDown(){
        Mockito.verifyNoMoreInteractions(mealRepository)
    }

    @Test
    fun setFavoriteMeal() = runBlocking{
        val expectResult = DataDummy.generateDummyMeal()
        setFavoriteMealUseCase(expectResult,false)
        Mockito.verify(mealRepository).setFavoriteMeal(expectResult,false)

    }
}