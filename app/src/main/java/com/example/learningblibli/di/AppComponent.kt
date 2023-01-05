package com.example.learningblibli.di

import android.content.Context
import com.example.learningblibli.MyApplication
import com.example.learningblibli.core.di.CoreModule
import com.example.learningblibli.core.di.UseCaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [CoreModule::class,UseCaseModule::class, ViewModelModule::class,AndroidInjectionModule::class,ActivityModule::class,FragmentModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(application: MyApplication)

}