package com.example.learningblibli.feature_detail.di

import com.example.learningblibli.feature_detail.ui.DetailFragment
import dagger.Subcomponent

@DetailScope
@Subcomponent(
    modules = [DetailModule::class, DetailViewModelModule::class]
)
interface DetailComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailComponent
    }

    fun inject(detailFragment: DetailFragment)
}