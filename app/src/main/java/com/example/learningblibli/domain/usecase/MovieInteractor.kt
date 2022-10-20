package com.example.learningblibli.domain.usecase

import com.example.learningblibli.data.repository.MovieRepository
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class MovieInteractor (private val movieRepository: MovieRepository):MovieUseCase {
    override fun getNowPlayingMovies(): Flow<Resource<List<Movie>>> {
        return movieRepository.getNowPlayingMovie()
    }

    override fun getMovieDetail(id: Int): Flow<Resource<Movie>> {
        return movieRepository.getMovieDetail(id)
    }

}