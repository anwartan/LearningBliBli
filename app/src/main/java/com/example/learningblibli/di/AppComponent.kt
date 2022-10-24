package com.example.learningblibli.di

import android.content.Context
import com.example.learningblibli.MainActivity
import com.example.learningblibli.ui.detail.DetailFragment
import com.example.learningblibli.ui.favorite.FavoriteFragment
import com.example.learningblibli.ui.home.HomeFragment
import com.example.learningblibli.ui.login.LoginFragment
import com.example.learningblibli.ui.register.RegisterFragment
import com.example.learningblibli.ui.search.SearchFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class,ViewModelModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
    fun inject(activity:MainActivity)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: FavoriteFragment)
    fun inject(fragment: DetailFragment)
    fun inject(fragment: SearchFragment)
    fun inject(fragment: LoginFragment)
    fun inject(fragment: RegisterFragment)
}