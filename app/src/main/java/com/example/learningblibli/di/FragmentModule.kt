package com.example.learningblibli.di

import com.example.learningblibli.feature_detail.di.DetailViewModelModule
import com.example.learningblibli.feature_detail.ui.DetailFragment
import com.example.learningblibli.feature_favorite.di.FavoriteViewModelModule
import com.example.learningblibli.feature_favorite.ui.FavoriteFragment
import com.example.learningblibli.ui.home.HomeFragment
import com.example.learningblibli.ui.login.LoginFragment
import com.example.learningblibli.ui.register.RegisterFragment
import com.example.learningblibli.ui.search.SearchFragment
import com.example.learningblibli.ui.setting.SettingFragment
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
    @ContributesAndroidInjector
    abstract fun bindSearchFragment():SearchFragment
    @ContributesAndroidInjector(modules = [DetailViewModelModule::class])
    abstract fun bindDetailFragment():DetailFragment
    @ContributesAndroidInjector
    abstract fun bindSettingFragment():SettingFragment
}