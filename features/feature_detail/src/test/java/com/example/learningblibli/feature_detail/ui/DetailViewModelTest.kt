package com.example.learningblibli.feature_detail.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.core.domain.usecase.contract.AddFavoriteMealUseCase
import com.example.learningblibli.core.domain.usecase.contract.GetFavoriteMealByIdUseCase
import com.example.learningblibli.core.domain.usecase.contract.GetMealDetailUseCase
import com.example.learningblibli.core.domain.usecase.contract.SetFavoriteMealUseCase
import com.example.learningblibli.feature_detail.utils.DataDummy
import com.example.learningblibli.feature_detail.utils.getOrAwaitValue
import com.example.learningblibli.lib_model.model.Meal
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
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
    private lateinit var getFavoriteMealByIdUsecase: GetFavoriteMealByIdUseCase

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
        viewModel.getDetailMeal(dataDummy.idMeal)
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
        viewModel.getDetailMeal(dataDummy.idMeal)
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
        viewModel.getDetailMeal(dataDummy.idMeal)
        val actualResult = viewModel.detailMeal.getOrAwaitValue()
        Assert.assertTrue(actualResult is Resource.Error)
        Assert.assertNull(actualResult.data)
        Assert.assertEquals("ERROR",actualResult.message)
        Mockito.verify(getMealDetailUseCase).invoke(dataDummy.idMeal)
    }

    @Test
    fun getFavoriteMeal() = runTest{
        val dataDummy = DataDummy.generateDummyMeal()

        Mockito.`when`(getFavoriteMealByIdUsecase(dataDummy.idMeal)).thenReturn(dataDummy)
        viewModel.getFavoriteMeal(dataDummy.idMeal)
        val actualResult = viewModel.isFavorite.getOrAwaitValue()
        Mockito.verify(getFavoriteMealByIdUsecase).invoke(dataDummy.idMeal)
        Assert.assertEquals(dataDummy.isFavorite,actualResult)
    }

    @Test
    fun setFavoriteMeal() = runTest {
        val dataDummy = DataDummy.generateDummyMeal()

        Mockito.`when`(getFavoriteMealByIdUsecase(dataDummy.idMeal)).thenReturn(dataDummy)
        viewModel.setFavoriteMeal(dataDummy)
        Mockito.verify(getFavoriteMealByIdUsecase,times(2)).invoke(dataDummy.idMeal)
        Mockito.verify(setFavoriteMealUseCase).invoke(dataDummy,!dataDummy.isFavorite)

    }


    @Test
    fun setNewFavoriteMeal() = runTest{
        val dataDummy = DataDummy.generateDummyMeal()
        Mockito.`when`(getFavoriteMealByIdUsecase(dataDummy.idMeal)).thenReturn(null)
        viewModel.setFavoriteMeal(dataDummy)
        Mockito.verify(addFavoriteMealUseCase).invoke(dataDummy)
        Mockito.verify(getFavoriteMealByIdUsecase,times(2)).invoke(dataDummy.idMeal)


    }



}