package com.example.learningblibli.core.domain.usecase

import com.example.learningblibli.core.data.repository.MealRepository
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.core.domain.usecase.impl.SearchMealUseCaseImpl
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

class SearchMealUseCaseImplTest{
    @Mock
    private lateinit var mealRepository: MealRepository

    @InjectMocks
    private lateinit var searchMealUseCaseImpl: SearchMealUseCaseImpl

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)

    }
    @After
    fun tearDown(){
        Mockito.verifyNoMoreInteractions(mealRepository)
    }

    @Test
    fun searchMeal(){
        val dataDummy = DataDummy.generateDummyListMealResponse()
        val expectResult = Observable.just(dataDummy)
        Mockito.`when`(mealRepository.searchMeal("a")).thenReturn(expectResult)
        val actualResult = searchMealUseCaseImpl.invoke("a").blockingFirst()
        Assert.assertTrue(actualResult is Resource.Success)
        Assert.assertNotNull(actualResult.data)
        Assert.assertEquals(dataDummy.meals?.size,actualResult.data?.size)
        Mockito.verify(mealRepository).searchMeal("a")
    }
}