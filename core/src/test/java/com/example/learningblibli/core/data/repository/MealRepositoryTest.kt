package com.example.learningblibli.core.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.learningblibli.core.data.source.local.room.MealDao
import com.example.learningblibli.core.utils.DataDummy
import com.example.learningblibli.core.utils.getOrAwaitValue
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MealRepositoryTest{
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiService: com.example.learningblibli.lib_api.service.ApiService
    @Mock
    private lateinit var mealDao: MealDao
    @InjectMocks
    private lateinit var mealRepository: MealRepository
    private val dispatcher =  UnconfinedTestDispatcher()

    @Before
    fun setUp(){
        Dispatchers.setMain(dispatcher)

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()

        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()

        Mockito.verifyNoMoreInteractions(mealDao)
        Mockito.verifyNoMoreInteractions(apiService)
    }

    @Test
    fun getAllMealsByFirstLetter(){
        val dataDummy = DataDummy.generateDummyListMealResponse()
        val data = Observable.just(dataDummy)
        Mockito.`when`(apiService.getAllMealsByFirstLetter("a")).thenReturn(data)
        val result = mealRepository.getAllMealsByFirstLetter("a").blockingFirst()
        Assert.assertNotNull(result.meals)
        Assert.assertEquals(dataDummy.meals?.size,result.meals?.size)
        Assert.assertEquals(dataDummy.meals?.get(0), result.meals?.get(0))
        Mockito.verify(apiService).getAllMealsByFirstLetter("a")
    }

    @Test
    fun getMealDetail(){
        val dataDummy = DataDummy.generateDummyListMealResponse()
        val data = Observable.just(dataDummy)
        Mockito.`when`(apiService.getMealDetail(1)).thenReturn(data)
        val result = mealRepository.getMealDetail(1).blockingFirst()
        Assert.assertNotNull(result.meals)
        Assert.assertEquals(dataDummy.meals?.size,result.meals?.size)
        Assert.assertEquals(dataDummy.meals?.get(0), result.meals?.get(0))
        Mockito.verify(apiService).getMealDetail(1)

    }
    @Test
    fun searchMeal(){
        val dataDummy = DataDummy.generateDummyListMealResponse()
        val data = Observable.just(dataDummy)
        Mockito.`when`(apiService.searchMeal("a")).thenReturn(data)
        val result = mealRepository.searchMeal("a").blockingFirst()
        Assert.assertNotNull(result.meals)
        Assert.assertEquals(dataDummy.meals?.size,result.meals?.size)
        Assert.assertEquals(dataDummy.meals?.get(0), result.meals?.get(0))
        Mockito.verify(apiService).searchMeal("a")
    }

    @Test
    fun setFavoriteMeal() = runTest{
        val dummyMeal = DataDummy.generateMealEntity()
        mealRepository.setFavoriteMeal(dummyMeal,false)
        Mockito.verify(mealDao).setFavoriteById(dummyMeal.idMeal,false)
    }

    @Test
    fun insertFavoriteMeal() = runTest{
        val dummyMealEntity = DataDummy.generateMealEntity()
        mealRepository.insertFavoriteMeal(dummyMealEntity)
        Mockito.verify(mealDao).insertMeal(dummyMealEntity)
    }

    @Test
    fun getFavoriteMealById() = runTest{
        val dummyMealEntity = DataDummy.generateMealEntity()
        Mockito.`when`(mealDao.getMealById(dummyMealEntity.idMeal)).thenReturn(dummyMealEntity)
        val actual  = mealRepository.getFavoriteMealById(dummyMealEntity.idMeal)
        Assert.assertNotNull(actual)
        Assert.assertEquals(actual,dummyMealEntity)
        Mockito.verify(mealDao).getMealById(dummyMealEntity.idMeal)
    }

    @Test
    fun getFavoriteMeals() = runTest {
        val dummyMealEntities = DataDummy.generateMealEntities()
        val expectResult = MutableLiveData(dummyMealEntities)
        Mockito.`when`(mealDao.getFavoriteMeals()).thenReturn(expectResult)
        val actual = mealRepository.getFavoriteMeals().getOrAwaitValue()
        Assert.assertEquals(dummyMealEntities.size,actual.size)
        Assert.assertEquals(dummyMealEntities[0].idMeal,actual[0].idMeal)
        Mockito.verify(mealDao).getFavoriteMeals()
    }
}