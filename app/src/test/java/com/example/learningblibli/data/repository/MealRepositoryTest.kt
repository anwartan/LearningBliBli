package com.example.learningblibli.data.repository

import com.example.learningblibli.data.source.local.LocalDataSource
import com.example.learningblibli.data.source.remote.RemoteDataSource
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.data.source.remote.network.ApiResponse
import com.example.learningblibli.data.source.remote.response.ListMealResponse
import com.example.learningblibli.utils.DataDummy
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MealRepositoryTest{
    @Mock
    private lateinit var remoteDataSource: RemoteDataSource
    @Mock
    private lateinit var localDataSource: LocalDataSource
    private lateinit var mealRepository:MealRepository
    @Before
    fun setUp(){

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        mealRepository = MealRepository(remoteDataSource,localDataSource)
    }
    @After
    fun tearDown(){
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    @Test
    fun getAllMealsByFirstLetter(){
        val dataDummy = DataDummy.generateDummyListMealResponse()
        val expectedData = ApiResponse.Success(dataDummy)
        val data = Observable.just(expectedData as ApiResponse<ListMealResponse>)
        Mockito.`when`(remoteDataSource.getAllMealsByFirstLetter("a")).thenReturn(data)
        val observer = mealRepository.getAllMealsByFirstLetter("a").subscribe {
            Assert.assertTrue(it is Resource.Success)
        }
    }
}