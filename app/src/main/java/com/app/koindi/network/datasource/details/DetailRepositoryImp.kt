package com.app.koindi.network.datasource.details

import com.app.koindi.global.API_KEY
import com.app.koindi.models.State
import com.app.koindi.models.responses.ActorsListResponse
import com.app.koindi.network.MovieApiService
import com.app.koindi.network.datasource.NetworkBoundRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class DetailRepositoryImp(private val mMovieApiService: MovieApiService) :
    DetailRepository {

    @ExperimentalCoroutinesApi
    override fun fetchActorsList(movieId: Int): Flow<State<ActorsListResponse>> {
        return object : NetworkBoundRepository<ActorsListResponse>() {
            override suspend fun fetchFromRemote(): Response<ActorsListResponse> =
                mMovieApiService.getActorsList(movieId.toString(), API_KEY)
        }.asFlow()
    }
}