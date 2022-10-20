package com.example.learningblibli.utils.mapper

import com.example.learningblibli.data.source.local.entity.MovieEntity
import com.example.learningblibli.data.source.remote.response.MovieResponse
import com.example.learningblibli.domain.model.Movie

object MovieMapper {
    fun mapResponseToModel(movieResponse: MovieResponse):Movie{
        return Movie(
            id = movieResponse.id,
            overview = movieResponse.overview,
            originalLanguage = movieResponse.originalLanguage,
            originalTitle = movieResponse.originalTitle,
            video = movieResponse.video,
            title = movieResponse.title,
            posterPath = movieResponse.posterPath,
            popularity = movieResponse.popularity,
            backdropPath = movieResponse.backdropPath,
            releaseDate = movieResponse.releaseDate,
            adult = movieResponse.adult,
            voteAverage = movieResponse.voteAverage,
            voteCount = movieResponse.voteCount,
        )
    }
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                video = it.video,
                title = it.title,
                posterPath = it.posterPath,
                popularity = it.popularity,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                adult = it.adult,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,

            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                video = it.video,
                title = it.title,
                posterPath = it.posterPath,
                popularity = it.popularity,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                adult = it.adult,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount
            )
        }

    private fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        overview = input.overview,
        originalLanguage = input.originalLanguage,
        originalTitle = input.originalTitle,
        video = input.video,
        title = input.title,
        posterPath = input.posterPath,
        popularity = input.popularity,
        backdropPath = input.backdropPath,
        releaseDate = input.releaseDate,
        adult = input.adult,
        voteAverage = input.voteAverage,
        voteCount = input.voteCount
    )
    fun mapEntityToDomain(input: MovieEntity) = Movie(
        id = input.id,
        overview = input.overview,
        originalLanguage = input.originalLanguage,
        originalTitle = input.originalTitle,
        video = input.video,
        title = input.title,
        posterPath = input.posterPath,
        popularity = input.popularity,
        backdropPath = input.backdropPath,
        releaseDate = input.releaseDate,
        adult = input.adult,
        voteAverage = input.voteAverage,
        voteCount = input.voteCount
    )
}