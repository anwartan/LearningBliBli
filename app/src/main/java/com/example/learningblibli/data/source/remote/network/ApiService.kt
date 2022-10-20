package com.example.learningblibli.data.source.remote.network

import com.example.learningblibli.data.source.remote.response.ListMovieResponse
import com.example.learningblibli.data.source.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): ListMovieResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(): ListMovieResponse

    @GET("movie/top_rated")
    suspend  fun getTopRatedMovies(): ListMovieResponse

    @GET("movie/{id}")
    suspend fun getMovieDetail(@Path("id") id: Int): MovieResponse
}