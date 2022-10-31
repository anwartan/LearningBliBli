package com.example.learningblibli.feature_detail.di

import androidx.lifecycle.ViewModel
import com.example.learningblibli.core.di.ViewModelKey
import com.example.learningblibli.feature_detail.ui.DetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel):ViewModel
}