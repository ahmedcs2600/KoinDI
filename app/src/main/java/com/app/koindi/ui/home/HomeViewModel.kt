package com.app.koindi.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.koindi.models.State
import com.app.koindi.models.responses.MoviesListResponse
import com.app.koindi.network.datasource.home.HomeRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel (private val mRepository: HomeRepository) : ViewModel() {

    private val _moviesList = MutableLiveData<State<MoviesListResponse>>()
    val moviesList: LiveData<State<MoviesListResponse>> get() = _moviesList

    init {
        fetchMovies()
    }

   private fun fetchMovies() {
        viewModelScope.launch {
            mRepository.fetchMovies().collect {
                _moviesList.value = it
            }
        }
    }
}