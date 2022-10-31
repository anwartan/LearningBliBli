package com.example.learningblibli.core.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.learningblibli.core.data.repository.MealRepository
import com.example.learningblibli.core.utils.DataDummy
import com.example.learningblibli.core.utils.getOrAwaitValue
import com.example.learningblibli.core.utils.mapper.MealMapper
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
        val dataDummy =DataDummy.generateMealEntities()
        val dataDummyMealModels = MealMapper.mapEntitiesToModels(dataDummy)
        val expect = MutableLiveData(dataDummy)
        Mockito.`when`(mealRepository.getFavoriteMeals()).thenReturn(expect)
        val actualResult = getFavoriteMealUsecase().getOrAwaitValue()
        Assert.assertEquals(dataDummyMealModels.size,actualResult.size)
        Assert.assertEquals(dataDummyMealModels[0],actualResult[0])
        Mockito.verify(mealRepository).getFavoriteMeals()
    }

}