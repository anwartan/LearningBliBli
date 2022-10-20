package com.example.learningblibli.data.repository

import com.example.learningblibli.data.NetworkAndFetch
import com.example.learningblibli.data.source.remote.RemoteDataSource
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.data.source.remote.network.ApiResponse
import com.example.learningblibli.data.source.remote.response.MovieResponse
import com.example.learningblibli.domain.model.Movie
import com.example.learningblibli.domain.repository.IMovieRepository
import com.example.learningblibli.utils.mapper.MovieMapper
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val remoteDataSource: RemoteDataSource) :IMovieRepository {
    override fun getNowPlayingMovie(): Flow<Resource<List<Movie>>> {
       return object : NetworkAndFetch<List<Movie>,List<MovieResponse>>(){
           override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
               return remoteDataSource.getNowPlayingMovies()
           }

           override fun mapResponse(data: List<MovieResponse>): List<Movie> {
               val entity = MovieMapper.mapResponsesToEntities(data)
               return MovieMapper.mapEntitiesToDomain(entity)
           }

       }.asFlow()
    }

    override fun getMovieDetail(id: Int): Flow<Resource<Movie>> {
        return object : NetworkAndFetch<Movie,MovieResponse>(){
            override suspend fun createCall(): Flow<ApiResponse<MovieResponse>> {
                return remoteDataSource.getMovieDetail(id)
            }

            override fun mapResponse(data: MovieResponse): Movie {

                return MovieMapper.mapResponseToModel(data)
            }

        }.asFlow()
    }
}