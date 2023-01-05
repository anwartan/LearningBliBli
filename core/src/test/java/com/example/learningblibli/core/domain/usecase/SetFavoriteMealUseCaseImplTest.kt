package com.example.learningblibli.core.domain.usecase

import com.example.learningblibli.core.data.repository.MealRepository
import com.example.learningblibli.core.domain.usecase.impl.SetFavoriteMealUseCaseImpl
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


class SetFavoriteMealUseCaseImplTest{
    @Mock
    private lateinit var mealRepository: MealRepository

    @InjectMocks
    private lateinit var setFavoriteMealUseCaseImpl: SetFavoriteMealUseCaseImpl

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
    }

    @After
    fun tearDown(){
        Mockito.verifyNoMoreInteractions(mealRepository)
    }

    @Test
    fun setFavoriteMeal() = runBlocking{
        val expectResult = DataDummy.generateDummyMeal()
        val expectEntity = MealMapper.mapModelToEntity(expectResult)
        setFavoriteMealUseCaseImpl(expectResult,false)
        Mockito.verify(mealRepository).setFavoriteMeal(expectEntity,false)

    }
}