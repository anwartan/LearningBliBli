package com.example.learningblibli.core.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.learningblibli.core.domain.usecase.GetFavoriteMealUsecase
import com.example.learningblibli.core.data.repository.MealRepository
import com.example.learningblibli.core.utils.DataDummy
import com.example.learningblibli.core.utils.getOrAwaitValue
import org.junit.*

import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetFavoriteMealUsecaseTest{

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mealRepository: MealRepository

    @InjectMocks
    private lateinit var getFavoriteMealUsecase: GetFavoriteMealUsecase

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
    }

    @After
    fun tearDown(){
        Mockito.verifyNoMoreInteractions(mealRepository)
    }

    @Test
    fun getFavoriteMeal(){
        val dataDummy =DataDummy.generateDummyMeals()
        val expectResult = MutableLiveData(dataDummy)
        Mockito.`when`(mealRepository.getFavoriteMeals()).thenReturn(expectResult)
        val actualResult = getFavoriteMealUsecase().getOrAwaitValue()
        Assert.assertEquals(dataDummy.size,actualResult.size)
        Assert.assertEquals(dataDummy[0],actualResult[0])
        Mockito.verify(mealRepository).getFavoriteMeals()
    }

}