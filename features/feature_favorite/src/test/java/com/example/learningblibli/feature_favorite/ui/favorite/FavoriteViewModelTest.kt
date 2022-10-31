package com.example.learningblibli.feature_favorite.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.learningblibli.core.domain.usecase.GetFavoriteMealUsecase
import com.example.learningblibli.feature_favorite.utils.DataDummy
import com.example.learningblibli.feature_favorite.utils.getOrAwaitValue
import org.junit.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class FavoriteViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getFavoriteMealUsecase: GetFavoriteMealUsecase

    @InjectMocks
    private lateinit var favoriteViewModel: com.example.learningblibli.feature_favorite.ui.FavoriteViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

    }

    @After
    fun tearDown() {
        Mockito.verifyNoMoreInteractions(getFavoriteMealUsecase)
    }

    @Test
    fun getMeals() {
        val dataDummy = DataDummy.generateDummyMeals()
        val expectResult = MutableLiveData(dataDummy)
        Mockito.`when`(getFavoriteMealUsecase()).thenReturn(expectResult)
        favoriteViewModel.getMeals()
        val actualResult = favoriteViewModel.meals.getOrAwaitValue()
        Assert.assertEquals(actualResult.size,dataDummy.size)
        Assert.assertEquals(actualResult[0].idMeal,dataDummy[0].idMeal)
        Mockito.verify(getFavoriteMealUsecase).invoke()
    }
}