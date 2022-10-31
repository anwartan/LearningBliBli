package com.example.learningblibli.feature_search.di

import androidx.lifecycle.ViewModel
import com.example.learningblibli.core.di.ViewModelKey
import com.example.learningblibli.feature_search.ui.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SearchViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel):ViewModel
}