package com.example.learningblibli.core.domain.usecase

import com.example.learningblibli.core.data.repository.MealRepository
import com.example.learningblibli.core.domain.usecase.impl.AddFavoriteMealUseCaseImpl
import com.example.learningblibli.core.utils.DataDummy
import com.example.learningblibli.core.utils.mapper.MealMapper
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class AddFavoriteMealUseCaseImplTest{
    @Mock
    private lateinit var mealRepository: MealRepository

    @InjectMocks
    private lateinit var addFavoriteMealUseCaseImpl: AddFavoriteMealUseCaseImpl
    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)

    }

    @After
    fun tearDown(){
        Mockito.verifyNoMoreInteractions(mealRepository)
    }

    @Test
    fun addFavoriteMeal() = runBlocking{
        val meal = DataDummy.generateDummyMeal()
        val mealEntity = MealMapper.mapModelToEntity(meal)
        addFavoriteMealUseCaseImpl(meal)
        Mockito.verify(mealRepository).insertFavoriteMeal(mealEntity)
    }

}