package com.example.learningblibli.utils.mapper

import com.example.learningblibli.data.source.remote.response.MovieDetailResponse
import com.example.learningblibli.domain.model.MovieDetail

object MovieDetailMapper {

    fun mapResponseToModel(movieDetailResponse: MovieDetailResponse): MovieDetail {
        return MovieDetail(
            overview = movieDetailResponse.overview,
            originalLanguage = movieDetailResponse.originalLanguage,
            originalTitle = movieDetailResponse.originalTitle,
            title = movieDetailResponse.title,
            posterPath = movieDetailResponse.posterPath,
            backdropPath = movieDetailResponse.backdropPath,
            revenue = movieDetailResponse.revenue,
            releaseDate = movieDetailResponse.releaseDate,
            popularity = movieDetailResponse.popularity,
            voteAverage = movieDetailResponse.voteAverage,
            tagline = movieDetailResponse.tagline,
            id = movieDetailResponse.id,
            adult = movieDetailResponse.adult,
            voteCount = movieDetailResponse.voteCount,
            status = movieDetailResponse.status
        )
    }
}