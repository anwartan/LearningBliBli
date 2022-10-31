package com.example.learningblibli.core.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.learningblibli.core.data.source.local.room.MealDao
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.core.utils.DataDummy
import com.example.learningblibli.core.utils.getOrAwaitValue
import com.example.learningblibli.core.utils.mapper.MealMapper
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
        val expected = MealMapper.mapListMealResponseToListMeal(dataDummy)
        val data = Observable.just(dataDummy)
        Mockito.`when`(apiService.getAllMealsByFirstLetter("a")).thenReturn(data)
        val result = mealRepository.getAllMealsByFirstLetter("a").blockingFirst()
        Assert.assertTrue(result is Resource.Success)
        Assert.assertNotNull(result.data)
        Assert.assertEquals(expected.size,result.data?.size)
        Assert.assertEquals(expected[0], result.data?.get(0))
        Mockito.verify(apiService).getAllMealsByFirstLetter("a")
    }

    @Test
    fun getMealDetail(){
        val dataDummy = DataDummy.generateDummyListMealResponse()
        val expected = MealMapper.mapListMealResponseToListMeal(dataDummy)
        val data = Observable.just(dataDummy)
        Mockito.`when`(apiService.getMealDetail(1)).thenReturn(data)
        val result = mealRepository.getMealDetail(1).blockingFirst()
        Assert.assertTrue(result is Resource.Success)
        Assert.assertNotNull(result.data)
        Assert.assertEquals(expected[0],result.data)
        Mockito.verify(apiService).getMealDetail(1)

    }
    @Test
    fun searchMeal(){
        val dataDummy = DataDummy.generateDummyListMealResponse()
        val expected = MealMapper.mapListMealResponseToListMeal(dataDummy)
        val data = Observable.just(dataDummy)
        Mockito.`when`(apiService.searchMeal("a")).thenReturn(data)
        val result = mealRepository.searchMeal("a").blockingFirst()
        Assert.assertTrue(result is Resource.Success)
        Assert.assertNotNull(result.data)
        Assert.assertEquals(expected.size,result.data?.size)
        Assert.assertEquals(expected[0], result.data?.get(0))
        Mockito.verify(apiService).searchMeal("a")
    }

    @Test
    fun setFavoriteMeal() = runTest{
        val dummyMeal = DataDummy.generateDummyMeal()
        mealRepository.setFavoriteMeal(dummyMeal,false)

        Mockito.verify(mealDao).setFavoriteById(dummyMeal.idMeal,false)
    }

    @Test
    fun insertFavoriteMeal() = runTest{
        val dummyMealEntity = DataDummy.generateMealEntity()
        val dummyMeal = MealMapper.mapEntityToModel(dummyMealEntity)
        mealRepository.insertFavoriteMeal(dummyMeal)
        Mockito.verify(mealDao).insertMeal(dummyMealEntity)
    }

    @Test
    fun getFavoriteMealById() = runTest{
        val dummyMealEntity = DataDummy.generateMealEntity()
        val dummyMeal = MealMapper.mapEntityToModel(dummyMealEntity)
        Mockito.`when`(mealDao.getMealById(dummyMealEntity.idMeal)).thenReturn(dummyMealEntity)
        val actual  = mealRepository.getFavoriteMealById(dummyMealEntity.idMeal)
        Assert.assertNotNull(actual)
        Assert.assertEquals(actual,dummyMeal)
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