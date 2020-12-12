package com.app.koindi.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.koindi.models.State
import com.app.koindi.models.responses.ActorsListResponse
import com.app.koindi.models.responses.MovieModel
import com.app.koindi.network.datasource.details.DetailRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val mDetailRepository: DetailRepository,
    private val movieItem: MovieModel
) : ViewModel() {

    private val _actorsList = MutableLiveData<State<ActorsListResponse>>()
    val actorList: LiveData<State<ActorsListResponse>> get() = _actorsList

    private val _currentMovie = MutableLiveData<MovieModel>(movieItem)
    val currentMovie: LiveData<MovieModel> get() = _currentMovie


    init {
        fetchActor()
    }

    private fun fetchActor() {
        viewModelScope.launch {
            mDetailRepository.fetchActorsList(movieItem.id).collect { state ->
                _actorsList.value = state
            }
        }
    }
}