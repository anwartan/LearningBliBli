package com.example.learningblibli.ui.detail

import androidx.lifecycle.*
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.domain.model.Movie
import com.example.learningblibli.domain.usecase.MovieUseCase

class DetailViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    private val idMovie = MutableLiveData<Int>()

    val detailMovie: LiveData<Resource<Movie>> = Transformations.switchMap(idMovie) {
        movieUseCase.getMovieDetail(it).asLiveData()
    }

    fun setIdMovie(id:Int){
        idMovie.postValue(id)
    }
}