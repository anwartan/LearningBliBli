package com.example.learningblibli.core.domain.usecase

import com.example.learningblibli.core.data.repository.MealRepository
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.core.domain.usecase.impl.GetMealsByFirstNameUseCaseImpl
import com.example.learningblibli.core.utils.DataDummy
import io.reactivex.Observable
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class GetMealsByFirstNameUseCaseImplTest{

    @Mock
    private lateinit var mealRepository: MealRepository
    @InjectMocks
    private lateinit var getMealsByFirstNameUseCaseImpl: GetMealsByFirstNameUseCaseImpl

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
    }
    @After
    fun tearDown(){
        verifyNoMoreInteractions(mealRepository)
    }

    @Test
    fun getAllMealsByFirstLetter(){
        val dataDummy = DataDummy.generateDummyListMealResponse()
        val expectedMeals = Observable.just( dataDummy)

        `when`(mealRepository.getAllMealsByFirstLetter("a")).thenReturn(expectedMeals)
        val actualMeals = getMealsByFirstNameUseCaseImpl.invoke("a").blockingFirst()

        Assert.assertTrue(actualMeals is Resource.Success)
        Assert.assertNotNull(actualMeals.data)
        Assert.assertEquals(dataDummy.meals?.size, actualMeals.data?.size)
        Assert.assertEquals(dataDummy.meals?.get(0)?.idMeal, actualMeals.data?.get(0)?.idMeal)
        verify(mealRepository).getAllMealsByFirstLetter("a")
    }
}