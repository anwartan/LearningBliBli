package com.example.learningblibli.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.core.domain.usecase.GetMealsByFirstNameUseCase
import com.example.learningblibli.lib_model.model.Meal
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

class HomeViewModelTest {
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
    fun `when Get Meals By First Name Should Not Null and Return List Meal`() {
        val dataDummy = DataDummy.generateDummyMeals()
        val expectedMeals = Observable.just(Resource.Success(dataDummy) as Resource<List<Meal>>)
        `when`(getMealsByFirstNameUseCase("a")).thenReturn(expectedMeals)
        homeViewModel.getMeals()
        val actualMeals = homeViewModel.meals.getOrAwaitValue()
        Assert.assertNotNull(actualMeals)
        Assert.assertEquals(dataDummy.size, actualMeals.size)
        Assert.assertEquals(dataDummy[0], actualMeals[0])
        verify(getMealsByFirstNameUseCase).invoke("a")
    }

    @Test
    fun `when Get RecommendationMeals Should Not Null and Return List Meal`() {
        val dataDummy = DataDummy.generateDummyMeals()
        val expectedMeals = Observable.just(Resource.Success(dataDummy) as Resource<List<Meal>>)
        `when`(getMealsByFirstNameUseCase("b")).thenReturn(expectedMeals)
        homeViewModel.getRecommendationMeals()
        val actualMeals = homeViewModel.recommendationMeal.getOrAwaitValue()
        Assert.assertNotNull(actualMeals)

        Assert.assertEquals(dataDummy.size, actualMeals.size)
        Assert.assertEquals(dataDummy[0], actualMeals[0])
        verify(getMealsByFirstNameUseCase).invoke("b")
    }

    @Test
    fun `when Get NewMeals Should Not Null and Return List Meal`() {
        val dataDummy = DataDummy.generateDummyMeals()
        val expectedMeals = Observable.just(Resource.Success(dataDummy) as Resource<List<Meal>>)
        `when`(getMealsByFirstNameUseCase("s")).thenReturn(expectedMeals)
        homeViewModel.getNewMeals()
        val actualMeals = homeViewModel.newMeals.getOrAwaitValue()
        Assert.assertNotNull(actualMeals)
        Assert.assertEquals(dataDummy.size, actualMeals.size)
        Assert.assertEquals(dataDummy[0], actualMeals[0])
        verify(getMealsByFirstNameUseCase).invoke("s")
    }

}