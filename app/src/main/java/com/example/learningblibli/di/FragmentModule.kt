package com.example.learningblibli.di

import com.example.learningblibli.feature_detail.di.DetailViewModelModule
import com.example.learningblibli.feature_detail.ui.DetailFragment
import com.example.learningblibli.feature_favorite.di.FavoriteViewModelModule
import com.example.learningblibli.feature_favorite.ui.FavoriteFragment
import com.example.learningblibli.feature_search.di.SearchViewModelModule
import com.example.learningblibli.feature_search.ui.SearchFragment
import com.example.learningblibli.feature_setting.di.SettingViewModelModule
import com.example.learningblibli.feature_setting.ui.SettingFragment
import com.example.learningblibli.ui.home.HomeFragment
import com.example.learningblibli.ui.login.LoginFragment
import com.example.learningblibli.ui.register.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector(modules = [FavoriteViewModelModule::class])
    abstract fun bindFavoriteFragment(): FavoriteFragment
    @ContributesAndroidInjector
    abstract fun bindHomeFragment():HomeFragment
    @ContributesAndroidInjector
    abstract fun bindLoginFragment():LoginFragment
    @ContributesAndroidInjector
    abstract fun bindRegisterFragment():RegisterFragment
    @ContributesAndroidInjector(modules = [SearchViewModelModule::class])
    abstract fun bindSearchFragment(): SearchFragment
    @ContributesAndroidInjector(modules = [DetailViewModelModule::class])
    abstract fun bindDetailFragment():DetailFragment
    @ContributesAndroidInjector(modules = [SettingViewModelModule::class])
    abstract fun bindSettingFragment(): SettingFragment
}