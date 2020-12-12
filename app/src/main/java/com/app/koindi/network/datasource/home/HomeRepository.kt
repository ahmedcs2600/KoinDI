package com.app.koindi.network.datasource.home

import com.app.koindi.models.State
import com.app.koindi.models.responses.MoviesListResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun fetchMovies() : Flow<State<MoviesListResponse>>
}