package com.example.learningblibli.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.example.learningblibli.data.repository.MealRepository
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.utils.DataDummy
import com.example.learningblibli.utils.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMealsByFirstNameUseCaseTest{
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mealRepository: MealRepository
    private lateinit var getMealsByFirstNameUseCase: GetMealsByFirstNameUseCase

    private val dispatcher =  UnconfinedTestDispatcher()

    @Before
    fun setUp(){
        Dispatchers.setMain(dispatcher)
        getMealsByFirstNameUseCase = GetMealsByFirstNameUseCase(mealRepository)
    }
    @After
    fun tearDown(){
        Dispatchers.resetMain()
        verifyNoMoreInteractions(mealRepository)
    }

    @Test
    fun getAllMealsByFirstLetter(){
        val dataDummy = DataDummy.generateDummyMeals()
        val expectedMeals = flowOf( Resource.Success(dataDummy))

        `when`(mealRepository.getAllMealsByFirstLetter("a")).thenReturn(expectedMeals)
        val actualMeals = getMealsByFirstNameUseCase.invoke("a").asLiveData().getOrAwaitValue()
        verify(mealRepository).getAllMealsByFirstLetter("a")
        Assert.assertNotNull(actualMeals)
        Assert.assertTrue(actualMeals is Resource.Success)
        Assert.assertNotNull((actualMeals as Resource.Success).data)
        Assert.assertEquals(dataDummy.size, actualMeals.data?.size)
        Assert.assertEquals(dataDummy[0], actualMeals.data?.get(0))

    }
}