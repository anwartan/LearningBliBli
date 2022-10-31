package com.example.learningblibli.core.domain.usecase

import com.example.learningblibli.core.data.repository.MealRepository
import com.example.learningblibli.core.utils.DataDummy
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class GetFavoriteMealByIdUsecaseTest{

    @Mock
    private lateinit var mealRepository: MealRepository
    @InjectMocks
    private lateinit var getFavoriteMealByIdUsecase: GetFavoriteMealByIdUsecase

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
    }
    @After
    fun tearDown(){
        Mockito.verifyNoMoreInteractions(mealRepository)
    }
    @Test
    fun getFavoriteMealById():Unit = runBlocking{
        val expectResult = DataDummy.generateDummyMeal()
        Mockito.`when`( mealRepository.getFavoriteMealById(1)).thenReturn(expectResult)
        val actualResult = getFavoriteMealByIdUsecase(1)
        Assert.assertNotNull(actualResult)
        Assert.assertEquals(expectResult.idMeal,actualResult?.idMeal)
        Mockito.verify(mealRepository).getFavoriteMealById(1)
    }
}