package com.example.learningblibli.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.learningblibli.di.Injection
import com.example.learningblibli.ui.detail.DetailViewModel
import com.example.learningblibli.ui.favorite.FavoriteViewModel
import com.example.learningblibli.ui.home.HomeViewModel
import com.example.learningblibli.ui.watchlist.WatchlistViewModel

class ViewModelFactory :ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val movieUseCase =Injection.provideMovieUseCase()
        return when{
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {

                HomeViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {

                FavoriteViewModel() as T
            }
            modelClass.isAssignableFrom(WatchlistViewModel::class.java) -> {

                WatchlistViewModel() as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {

                DetailViewModel(movieUseCase) as T
            }
            else ->throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}