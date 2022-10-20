package com.example.learningblibli.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetail (

    val overview: String? = null,
    val originalLanguage: String,
    val originalTitle: String,
    val title: String,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val revenue: Int,
    val releaseDate: String,
    val popularity: Double,
    val voteAverage: Double,
    val tagline: String?=null,
    val id: Int,
    val adult: Boolean,
    val voteCount: Int,
    val status: String
):Parcelable
