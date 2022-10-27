package com.example.learningblibli.feature_detail.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.core.domain.model.Meal
import com.example.learningblibli.core.domain.usecase.AddFavoriteMealUseCase
import com.example.learningblibli.core.domain.usecase.GetFavoriteMealByIdUsecase
import com.example.learningblibli.core.domain.usecase.GetMealDetailUseCase
import com.example.learningblibli.core.domain.usecase.SetFavoriteMealUseCase
import com.example.learningblibli.feature_detail.utils.DataDummy
import com.example.learningblibli.feature_detail.utils.getOrAwaitValue
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {

    @Mock
    private lateinit var getMealDetailUseCase: GetMealDetailUseCase
    @Mock
    private lateinit var setFavoriteMealUseCase: SetFavoriteMealUseCase
    @Mock
    private lateinit var addFavoriteMealUseCase: AddFavoriteMealUseCase
    @Mock
    private lateinit var getFavoriteMealByIdUsecase: GetFavoriteMealByIdUsecase

    @InjectMocks
    private lateinit var viewModel: DetailViewModel
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Mockito.verifyNoMoreInteractions(getMealDetailUseCase)
        Mockito.verifyNoMoreInteractions(setFavoriteMealUseCase)
        Mockito.verifyNoMoreInteractions(addFavoriteMealUseCase)
        Mockito.verifyNoMoreInteractions(getFavoriteMealByIdUsecase)
        Dispatchers.resetMain()
    }

    @Test
    fun getDetailMovie() {
        val dataDummy = DataDummy.generateDummyMeal()
        val expect = Observable.just<Resource<Meal>>(Resource.Success(dataDummy))
        Mockito.`when`(getMealDetailUseCase(dataDummy.idMeal)).thenReturn(expect)
        viewModel.getDetailMeal(dataDummy)
        val actualResult = viewModel.detailMeal.getOrAwaitValue()
        Assert.assertTrue(actualResult is Resource.Success)
        Assert.assertNotNull(actualResult.data)
        Assert.assertEquals(dataDummy.idMeal,actualResult.data?.idMeal)
        Mockito.verify(getMealDetailUseCase).invoke(dataDummy.idMeal)
    }

    @Test
    fun getDetailMovieWhenLoading() {
        val dataDummy = DataDummy.generateDummyMeal()
        val expect = Observable.just<Resource<Meal>>(Resource.Loading())
        Mockito.`when`(getMealDetailUseCase(dataDummy.idMeal)).thenReturn(expect)
        viewModel.getDetailMeal(dataDummy)
        val actualResult = viewModel.detailMeal.getOrAwaitValue()
        Assert.assertTrue(actualResult is Resource.Loading)
        Assert.assertNull(actualResult.data)
        Mockito.verify(getMealDetailUseCase).invoke(dataDummy.idMeal)
    }
    @Test
    fun getDetailMovieWhenError() {
        val dataDummy = DataDummy.generateDummyMeal()
        val expect = Observable.just<Resource<Meal>>(Resource.Error("ERROR"))
        Mockito.`when`(getMealDetailUseCase(dataDummy.idMeal)).thenReturn(expect)
        viewModel.getDetailMeal(dataDummy)
        val actualResult = viewModel.detailMeal.getOrAwaitValue()
        Assert.assertTrue(actualResult is Resource.Error)
        Assert.assertNull(actualResult.data)
        Assert.assertEquals("ERROR",actualResult.message)
        Mockito.verify(getMealDetailUseCase).invoke(dataDummy.idMeal)
    }

//    @Test
//    fun setFavoriteMovie() = runBlocking {
//        val dataDummy = DataDummy.generateDummyMeal()
//
//        Mockito.`when`(getFavoriteMealByIdUsecase(dataDummy.idMeal)).thenReturn(dataDummy)
//        viewModel.setFavoriteMovie(dataDummy)
//        Mockito.verify(getFavoriteMealByIdUsecase).invoke(dataDummy.idMeal)
//        Mockito.verify(setFavoriteMealUseCase).invoke(dataDummy,!dataDummy.isFavorite)
//
//    }
//    @Test
//    fun setNewFavoriteMeal():Unit = runBlocking {
//        val dataDummy = DataDummy.generateDummyMeal()
//        Mockito.`when`(getFavoriteMealByIdUsecase(Mockito.anyInt())).thenReturn(null)
//        viewModel.setFavoriteMovie(dataDummy)
//        Mockito.verify(getFavoriteMealByIdUsecase).invoke(1)
//        Mockito.verify(addFavoriteMealUseCase).invoke(dataDummy)
//    }



}