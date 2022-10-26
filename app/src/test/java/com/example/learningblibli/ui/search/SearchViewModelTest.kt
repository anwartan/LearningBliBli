package com.example.learningblibli.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.domain.model.Meal
import com.example.learningblibli.domain.usecase.SearchMealUseCase
import com.example.learningblibli.utils.DataDummy
import com.example.learningblibli.utils.getOrAwaitValue
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.*
import org.mockito.*


class SearchViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var searchMealUseCase: SearchMealUseCase

    @InjectMocks
    private lateinit var searchViewModel: SearchViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @After
    fun tearDown() {
        Mockito.verifyNoMoreInteractions(searchMealUseCase)
    }

    @Test
    fun searchMeals() {
        val dataDummy = DataDummy.generateDummyMeals()
        val expectResult=Observable.just<Resource<List<Meal>>>(Resource.Success(dataDummy))
        Mockito.`when`(searchMealUseCase("a")).thenReturn(expectResult)
        searchViewModel.searchMeals("a")
        val actualResult = searchViewModel.meals.getOrAwaitValue()
        Assert.assertTrue(actualResult is Resource.Success)
        Assert.assertNotNull(actualResult.data)
        Assert.assertEquals(dataDummy.size,actualResult.data?.size)
        Mockito.verify(searchMealUseCase).invoke("a")
    }

    @Test
    fun searchMealsWithLoading() {
        val expectResult=Observable.just<Resource<List<Meal>>>(Resource.Loading())
        Mockito.`when`(searchMealUseCase("a")).thenReturn(expectResult)
        searchViewModel.searchMeals("a")
        val actualResult = searchViewModel.meals.getOrAwaitValue()
        Assert.assertTrue(actualResult is Resource.Loading)
        Assert.assertNull(actualResult.data)
        Mockito.verify(searchMealUseCase).invoke("a")
    }
    @Test
    fun searchMealsWithError() {
        val errorMessage = "ERROR"
        val expectResult=Observable.just<Resource<List<Meal>>>(Resource.Error(errorMessage))
        Mockito.`when`(searchMealUseCase("a")).thenReturn(expectResult)
        searchViewModel.searchMeals("a")
        val actualResult = searchViewModel.meals.getOrAwaitValue()
        Assert.assertTrue(actualResult is Resource.Error)
        Assert.assertNull(actualResult.data)
        Assert.assertEquals(errorMessage,actualResult.message)
        Mockito.verify(searchMealUseCase).invoke("a")
    }
}