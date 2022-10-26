package com.example.learningblibli.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.domain.model.Meal
import com.example.learningblibli.domain.usecase.GetMealsByFirstNameUseCase
import com.example.learningblibli.utils.DataDummy
import com.example.learningblibli.utils.getOrAwaitValue
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class HomeViewModelTest{
    @Mock
    private lateinit var getMealsByFirstNameUseCase: GetMealsByFirstNameUseCase
    @InjectMocks
    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(getMealsByFirstNameUseCase)
    }
    @Test
    fun `when Get Meals By First Name Should Not Null and Return Resource Success`(){
        val dataDummy = DataDummy.generateDummyMeals()
        val expectedMeals = Observable.just( Resource.Success(dataDummy) as Resource<List<Meal>>)
        `when`(getMealsByFirstNameUseCase("a")).thenReturn(expectedMeals)
        homeViewModel.getMeals()
        val actualMeals = homeViewModel.meals.getOrAwaitValue ()
        Assert.assertNotNull(actualMeals)
        Assert.assertTrue(actualMeals is Resource.Success)
        Assert.assertNotNull((actualMeals as Resource.Success).data)
        Assert.assertEquals(dataDummy.size, actualMeals.data?.size)
        Assert.assertEquals(dataDummy[0], actualMeals.data?.get(0))
        verify(getMealsByFirstNameUseCase).invoke("a")
    }

    @Test
    fun `when Get Meals By First Name Should Not Null and Return Resource Loading`(){

        val expectedMeals = Observable.just<Resource<List<Meal>>>( Resource.Loading())
        `when`(getMealsByFirstNameUseCase("a")).thenReturn(expectedMeals)
        homeViewModel.getMeals()
        verify(getMealsByFirstNameUseCase).invoke("a")
        val actualMeals = homeViewModel.meals.getOrAwaitValue()
        Assert.assertNotNull(actualMeals)
        Assert.assertTrue(actualMeals is Resource.Loading)

    }

    @Test
    fun `when Get Meals By First Name Should Not Null and Return Resource Error`(){
        val message ="ERROR"
        val expected = Observable.just<Resource<List<Meal>>>( Resource.Error(message))
        `when`(getMealsByFirstNameUseCase("a")).thenReturn(expected)
        homeViewModel.getMeals()
        verify(getMealsByFirstNameUseCase).invoke("a")
        val actualMeals = homeViewModel.meals.getOrAwaitValue()
        Assert.assertNotNull(actualMeals)
        Assert.assertTrue(actualMeals is Resource.Error)
        Assert.assertNotNull((actualMeals as Resource.Error).message)
        Assert.assertEquals(message, actualMeals.message)
    }
}