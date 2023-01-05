package com.example.learningblibli.feature_search.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.core.domain.usecase.contract.SearchMealUseCase
import com.example.learningblibli.feature_search.utils.DataDummy
import com.example.learningblibli.feature_search.utils.getOrAwaitValue
import com.example.learningblibli.lib_model.model.Meal
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var searchMealUseCase: SearchMealUseCase

    @InjectMocks
    private lateinit var searchViewModel: SearchViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Mockito.verifyNoMoreInteractions(searchMealUseCase)
        Dispatchers.resetMain()

    }

    @Test
    fun searchMeals() {
        val dataDummy = DataDummy.generateDummyMeals()
        val expectResult=Observable.just<Resource<List<Meal>>>(Resource.Success(dataDummy))
        Mockito.`when`(searchMealUseCase("a")).thenReturn(expectResult)
        searchViewModel.searchMeals("a")
        val actualResult = searchViewModel.meals.getOrAwaitValue()
        Assert.assertNotNull(actualResult)
        Assert.assertEquals(dataDummy.size,actualResult.size)
        Mockito.verify(searchMealUseCase).invoke("a")
    }

    @Test
    fun searchMealsWithError() = runTest(testDispatcher) {
        val errorMessage = "ERROR"
        val expectResult=Observable.just<Resource<List<Meal>>>(Resource.Error(errorMessage))
        Mockito.`when`(searchMealUseCase("a")).thenReturn(expectResult)
        val results = mutableListOf<String>()
        val job = searchViewModel.error.onEach(results::add).launchIn(this)

        searchViewModel.searchMeals("a")
        Assert.assertEquals(1,results.size)
        Assert.assertEquals(errorMessage,results[0])
        Mockito.verify(searchMealUseCase).invoke("a")
        job.cancel()
    }
}