package com.example.learningblibli.domain.usecase

import com.example.learningblibli.data.repository.MealRepository
import com.example.learningblibli.utils.DataDummy
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations


class AddFavoriteMealUseCaseTest{
    @Mock
    private lateinit var mealRepository: MealRepository

    @InjectMocks
    private lateinit var addFavoriteMealUseCase: AddFavoriteMealUseCase
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
        addFavoriteMealUseCase(meal)
        Mockito.verify(mealRepository).insertFavoriteMeal(meal)
    }

}