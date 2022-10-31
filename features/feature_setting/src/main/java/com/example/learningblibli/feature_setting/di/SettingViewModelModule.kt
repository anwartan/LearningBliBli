package com.example.learningblibli.feature_setting.di

import androidx.lifecycle.ViewModel
import com.example.learningblibli.core.di.ViewModelKey
import com.example.learningblibli.feature_setting.ui.SettingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SettingViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SettingViewModel::class)
    abstract fun bindSettingViewModel(settingViewModel: SettingViewModel):ViewModel
}