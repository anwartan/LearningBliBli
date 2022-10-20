package com.example.learningblibli.domain.usecase


import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getNowPlayingMovies(): Flow<Resource<List<Movie>>>
    fun getMovieDetail(id: Int) : Flow<Resource<Movie>>
}