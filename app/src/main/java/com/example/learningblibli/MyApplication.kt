package com.example.learningblibli

import android.app.Application
import com.example.learningblibli.di.AppComponent
import com.example.learningblibli.di.DaggerAppComponent

class MyApplication : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(applicationContext)
    }


}