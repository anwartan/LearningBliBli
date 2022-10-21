package com.example.learningblibli.di

import android.content.Context
import com.example.learningblibli.BuildConfig
import com.example.learningblibli.data.repository.MealRepository
import com.example.learningblibli.data.source.local.LocalDataSource
import com.example.learningblibli.data.source.remote.RemoteDataSource
import com.example.learningblibli.data.source.remote.network.ApiConfig
import com.example.learningblibli.data.source.remote.network.ApiService
import com.example.learningblibli.domain.usecase.*
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Injection {

    private fun provideLocalDataSource(context: Context):LocalDataSource{
        return LocalDataSource()
    }

//    private fun provideMealDao(context: Context): MealDao {
//        return provideMealDatabase(context).mealDao()
//    }
//    private fun provideMealDatabase(context: Context):MealDatabase{
//        return MealDatabase.getDatabase(context)
//    }
    fun provideGetFavoriteMealUseCase(context: Context):GetFavoriteMealUsecase{
        return GetFavoriteMealUsecase(provideMealRepository(context))
    }

    fun provideGetMealDetail(context: Context):GetMealDetailUseCase{
        return GetMealDetailUseCase(provideMealRepository(context))
    }
    fun provideGetMealsByFirstNameUseCase(context: Context):GetMealsByFirstNameUseCase{
        return GetMealsByFirstNameUseCase(provideMealRepository(context))
    }
    fun provideSetFavoriteMealUseCase(context: Context):SetFavoriteMealUseCase{
        return SetFavoriteMealUseCase(provideMealRepository(context))
    }
    private fun provideMealRepository(context: Context): MealRepository {
        return MealRepository(provideRemoteDataSource(), provideLocalDataSource(context))
    }


    private fun provideRemoteDataSource():RemoteDataSource{
        return RemoteDataSource(provideApiService())
    }

    private fun provideOkHttpClient():OkHttpClient{


        val interceptor = Interceptor { chain ->
            val request = chain.request()

            val newUrl = request.url.newBuilder().addQueryParameter(
                ApiConfig.API_KEY_QUERY,
                ApiConfig.API_KEY
            ).build()
            val requestBuilder = request.newBuilder().url(newUrl)

            chain.proceed(requestBuilder.build())
        }
        val loggingInterceptor = if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        }else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(interceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()

    }

    private fun provideApiService(): ApiService {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()

        val client= provideOkHttpClient()
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }

    fun provideSearchMealUseCase(context: Context): SearchMealUseCase {
        return SearchMealUseCase(provideMealRepository(context))
    }
}