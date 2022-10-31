package com.example.learningblibli.core.domain.usecase

import com.example.learningblibli.core.data.repository.MealRepository
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.core.utils.DataDummy
import io.reactivex.Observable
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetMealDetailUseCaseTest{
    @Mock
    private lateinit var mealRepository: MealRepository

    @InjectMocks
    private lateinit var getMealDetailUseCase: GetMealDetailUseCase

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
    }
    @After
    fun tearDown(){
        Mockito.verifyNoMoreInteractions(mealRepository)
    }

    @Test
    fun getMealDetail(){
        val dataDummy = DataDummy.generateDummyListMealResponse()
        val expectResult = Observable.just(dataDummy)
        Mockito.`when`(mealRepository.getMealDetail(1)).thenReturn(expectResult)
        val actualResult = getMealDetailUseCase(1).blockingFirst()
        Assert.assertTrue(actualResult is Resource.Success)
        Assert.assertNotNull(actualResult.data)
        Assert.assertEquals(dataDummy.meals?.get(0)?.idMeal,actualResult.data?.idMeal)
        Mockito.verify(mealRepository).getMealDetail(1)
    }
}