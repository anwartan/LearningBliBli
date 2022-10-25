package com.example.learningblibli.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.domain.model.Meal
import com.example.learningblibli.domain.usecase.GetCurrentUserUseCase
import com.example.learningblibli.domain.usecase.GetMealsByFirstNameUseCase
import com.example.learningblibli.domain.usecase.LogoutByFirebaseUseCase
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
class HomeViewModelTest{
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var getMealsByFirstNameUseCase: GetMealsByFirstNameUseCase
    @Mock
    private lateinit var getCurrentUserUseCase: GetCurrentUserUseCase
    @Mock
    private lateinit var logoutByFirebaseUseCase: LogoutByFirebaseUseCase
    private lateinit var homeViewModel: HomeViewModel

    private val dispatcher =  UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        homeViewModel = HomeViewModel(getMealsByFirstNameUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        verifyNoMoreInteractions(getMealsByFirstNameUseCase)
    }
//    @Test
//    fun `when Get Meals By First Name Should Not Null and Return Resource Success`(){
//        val dataDummy = DataDummy.generateDummyMeals()
//        val expectedMeals = flowOf( Resource.Success(dataDummy))
//        `when`(getMealsByFirstNameUseCase("a")).thenReturn(expectedMeals)
//        homeViewModel.getMeals()
//        val actualMeals = homeViewModel.meals.getOrAwaitValue()
//        Assert.assertNotNull(actualMeals)
//        Assert.assertTrue(actualMeals is Resource.Success)
//        Assert.assertNotNull((actualMeals as Resource.Success).data)
//        Assert.assertEquals(dataDummy.size, actualMeals.data?.size)
//        Assert.assertEquals(dataDummy[0], actualMeals.data?.get(0))
//        verify(getMealsByFirstNameUseCase).invoke("a")
//    }
//
//    @Test
//    fun `when Get Meals By First Name Should Not Null and Return Resource Loading`(){
//
//        val expectedMeals = flowOf<Resource<List<Meal>>>( Resource.Loading())
//        `when`(getMealsByFirstNameUseCase("a")).thenReturn(expectedMeals)
//        homeViewModel.getMeals()
//        verify(getMealsByFirstNameUseCase).invoke("a")
//        val actualMeals = homeViewModel.meals.getOrAwaitValue()
//        Assert.assertNotNull(actualMeals)
//        Assert.assertTrue(actualMeals is Resource.Loading)
//
//    }
//
//    @Test
//    fun `when Get Meals By First Name Should Not Null and Return Resource Error`(){
//        val message ="ERROR"
//        val expected = flowOf<Resource<List<Meal>>>( Resource.Error(message))
//        `when`(getMealsByFirstNameUseCase("a")).thenReturn(expected)
//        homeViewModel.getMeals()
//        verify(getMealsByFirstNameUseCase).invoke("a")
//        val actualMeals = homeViewModel.meals.getOrAwaitValue()
//        Assert.assertNotNull(actualMeals)
//        Assert.assertTrue(actualMeals is Resource.Error)
//        Assert.assertNotNull((actualMeals as Resource.Error).message)
//        Assert.assertEquals(message, actualMeals.message)
//    }
}