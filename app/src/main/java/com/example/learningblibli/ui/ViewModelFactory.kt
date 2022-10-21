package com.example.learningblibli.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.learningblibli.di.Injection
import com.example.learningblibli.ui.detail.DetailViewModel
import com.example.learningblibli.ui.favorite.FavoriteViewModel
import com.example.learningblibli.ui.home.HomeViewModel
import com.example.learningblibli.ui.search.SearchViewModel
import com.example.learningblibli.ui.watchlist.WatchlistViewModel

class ViewModelFactory(private val context: Context) :ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                   context
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val getMealsByFirstNameUseCase = Injection.provideGetMealsByFirstNameUseCase(context)
        val getMealDetailUseCase = Injection.provideGetMealDetail(context)
        val searchMealUseCase = Injection.provideSearchMealUseCase(context)
        val getFavoriteMealUsecase = Injection.provideGetFavoriteMealUseCase(context)
        val setFavoriteMealUseCase = Injection.provideSetFavoriteMealUseCase(context)
        return when{
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {

                HomeViewModel(getMealsByFirstNameUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {

                FavoriteViewModel(getFavoriteMealUsecase) as T
            }
            modelClass.isAssignableFrom(WatchlistViewModel::class.java) -> {

                WatchlistViewModel() as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {

                DetailViewModel(getMealDetailUseCase,setFavoriteMealUseCase) as T
            }
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {

                SearchViewModel(searchMealUseCase) as T
            }
            else ->throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}