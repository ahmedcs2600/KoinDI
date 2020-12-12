package com.app.koindi.network.datasource.details

import com.app.koindi.models.State
import com.app.koindi.models.responses.ActorsListResponse
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun fetchActorsList(movieId : Int) : Flow<State<ActorsListResponse>>
}