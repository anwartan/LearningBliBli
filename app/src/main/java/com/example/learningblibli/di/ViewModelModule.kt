package com.example.learningblibli.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.learningblibli.ui.ViewModelFactory
import com.example.learningblibli.ui.detail.DetailViewModel
import com.example.learningblibli.ui.favorite.FavoriteViewModel
import com.example.learningblibli.ui.home.HomeViewModel
import com.example.learningblibli.ui.login.AuthViewModel
import com.example.learningblibli.ui.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun provideHomeViewModel(homeViewModel: HomeViewModel):ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun provideDetailViewModel(detailViewModel: DetailViewModel):ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun provideFavoriteViewModel(favoriteViewModel: FavoriteViewModel):ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun provideSearchViewModel(searchViewModel: SearchViewModel):ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun provideLoginViewModel(loginViewModel: AuthViewModel):ViewModel
}