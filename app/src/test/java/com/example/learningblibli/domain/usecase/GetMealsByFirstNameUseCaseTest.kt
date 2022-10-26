package com.example.learningblibli.domain.usecase

import com.example.learningblibli.data.repository.MealRepository
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.domain.model.Meal
import com.example.learningblibli.utils.DataDummy
import io.reactivex.Observable
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class GetMealsByFirstNameUseCaseTest{

    @Mock
    private lateinit var mealRepository: MealRepository
    @InjectMocks
    private lateinit var getMealsByFirstNameUseCase: GetMealsByFirstNameUseCase

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
        val dataDummy = DataDummy.generateDummyMeals()
        val expectedMeals = Observable.just( Resource.Success(dataDummy) as Resource<List<Meal>>)

        `when`(mealRepository.getAllMealsByFirstLetter("a")).thenReturn(expectedMeals)
        val actualMeals = getMealsByFirstNameUseCase.invoke("a").blockingFirst()
        verify(mealRepository).getAllMealsByFirstLetter("a")
        Assert.assertNotNull(actualMeals)
        Assert.assertTrue(actualMeals is Resource.Success)
        Assert.assertNotNull((actualMeals as Resource.Success).data)
        Assert.assertEquals(dataDummy.size, actualMeals.data?.size)
        Assert.assertEquals(dataDummy[0], actualMeals.data?.get(0))

    }
}