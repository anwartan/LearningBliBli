package com.example.learningblibli

import android.app.Application
import com.example.learningblibli.di.AppComponent
import com.example.learningblibli.di.DaggerAppComponent
import com.example.learningblibli.feature_detail.di.DetailComponent
import com.example.learningblibli.feature_detail.di.DetailComponentProvider

class MyApplication : Application(), DetailComponentProvider {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(applicationContext)
    }

    override fun provideDetailComponent(): DetailComponent {
        return appComponent.detailComponent().create()
    }


}