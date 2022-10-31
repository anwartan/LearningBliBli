package com.example.learningblibli.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.learningblibli.core.di.ViewModelKey
import com.example.learningblibli.core.ui.ViewModelFactory
import com.example.learningblibli.ui.home.HomeViewModel
import com.example.learningblibli.ui.login.AuthViewModel
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
    @ViewModelKey(AuthViewModel::class)
    abstract fun provideLoginViewModel(loginViewModel: AuthViewModel):ViewModel

}