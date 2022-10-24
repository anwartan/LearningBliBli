package com.example.learningblibli.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.example.learningblibli.data.source.local.LocalDataSource
import com.example.learningblibli.data.source.remote.RemoteDataSource
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.data.source.remote.network.ApiResponse
import com.example.learningblibli.data.source.remote.response.ListMealResponse
import com.example.learningblibli.utils.DataDummy
import com.example.learningblibli.utils.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MealRepositoryTest{

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val dispatcher = UnconfinedTestDispatcher()

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource
    @Mock
    private lateinit var localDataSource: LocalDataSource
    private lateinit var mealRepository:MealRepository
    @Before
    fun setUp(){
        Dispatchers.setMain(dispatcher)
        mealRepository = MealRepository(remoteDataSource,localDataSource)
    }
    @After
    fun tearDown(){
        Dispatchers.resetMain()
//        Mockito.verifyNoMoreInteractions(remoteDataSource)
//        Mockito.verifyNoMoreInteractions(localDataSource)
    }

    @Test
    fun getAllMealsByFirstLetter() = runBlocking{
        val expectedData = DataDummy.generateDummyListMealResponse()
        val data = flowOf<ApiResponse<ListMealResponse>>(ApiResponse.Success(expectedData))
        Mockito.`when`(remoteDataSource.getAllMealsByFirstLetter("a")).thenReturn(data)
        val actual = mealRepository.getAllMealsByFirstLetter("a").asLiveData()
        Assert.assertTrue(actual.getOrAwaitValue() is Resource.Loading)
        Assert.assertEquals(expectedData.meals?.size,(actual.getOrAwaitValue() as Resource.Success).data?.size)
        Mockito.verify(remoteDataSource).getAllMealsByFirstLetter("a")

    }

}