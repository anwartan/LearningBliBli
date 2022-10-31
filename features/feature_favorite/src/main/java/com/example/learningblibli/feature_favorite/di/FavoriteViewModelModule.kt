package com.example.learningblibli.feature_favorite.di

import androidx.lifecycle.ViewModel
import com.example.learningblibli.core.di.ViewModelKey
import com.example.learningblibli.feature_favorite.ui.FavoriteViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FavoriteViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindDetailViewModel(favoriteViewModel: FavoriteViewModel): ViewModel

}