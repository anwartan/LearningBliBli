package com.example.learningblibli.core.di

import com.example.learningblibli.core.domain.usecase.contract.*
import com.example.learningblibli.core.domain.usecase.impl.*
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule {

    @Binds
    abstract fun provideAddFavoriteMealUseCase(addFavoriteMealUseCaseImpl: AddFavoriteMealUseCaseImpl): AddFavoriteMealUseCase

    @Binds
    abstract fun provideGetCurrentUserUseCase(getCurrentUserUseCaseImpl: GetCurrentUserUseCaseImpl): GetCurrentUserUseCase

    @Binds
    abstract fun provideGetFavoriteMealByIdUseCase(getFavoriteMealByIdUsecaseImpl: GetFavoriteMealByIdUsecaseImpl): GetFavoriteMealByIdUseCase

    @Binds
    abstract fun provideGetFavoriteMealUseCase(getFavoriteMealUseCaseImpl: GetFavoriteMealUsecaseImpl): GetFavoriteMealUseCase

    @Binds
    abstract fun provideGetMealDetailUseCase(getMealDetailUseCaseImpl: GetMealDetailUseCaseImpl): GetMealDetailUseCase

    @Binds
    abstract fun provideGetMealsByFirstNameUseCase(getMealsByFirstNameUseCaseImpl: GetMealsByFirstNameUseCaseImpl): GetMealsByFirstNameUseCase

    @Binds
    abstract fun provideLoginByFirebaseUseCase(loginByFirebaseUseCaseImpl: LoginByFirebaseUseCaseImpl): LoginByFirebaseUseCase

    @Binds
    abstract fun provideLogoutByFirebaseUseCase(logoutByFirebaseUseCaseImpl: LogoutByFirebaseUseCaseImpl): LogoutByFirebaseUseCase

    @Binds
    abstract fun provideRegisterByFirebaseUseCase(registerByFirebaseUseCaseImpl: RegisterByFirebaseUseCaseImpl): RegisterByFirebaseUseCase

    @Binds
    abstract fun provideSearchMealUseCase(searchMealUseCaseImpl: SearchMealUseCaseImpl): SearchMealUseCase

    @Binds
    abstract fun provideSetFavoriteMealUseCase(setFavoriteMealUseCaseImpl: SetFavoriteMealUseCaseImpl): SetFavoriteMealUseCase

}