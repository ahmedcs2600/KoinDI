package com.app.koindi.network.datasource.home

import com.app.koindi.global.API_KEY
import com.app.koindi.models.State
import com.app.koindi.models.responses.MoviesListResponse
import com.app.koindi.network.MovieApiService
import com.app.koindi.network.datasource.NetworkBoundRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class HomeRepositoryImp (private val mMovieApiService : MovieApiService): HomeRepository {

    @ExperimentalCoroutinesApi
    override fun fetchMovies(): Flow<State<MoviesListResponse>> {
        return object : NetworkBoundRepository<MoviesListResponse>(){
            override suspend fun fetchFromRemote(): Response<MoviesListResponse> = mMovieApiService.getNowPlaying(API_KEY)
        }.asFlow()
    }
}