package com.app.koindi.models.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MoviesListResponse : BaseResponse()
{
    @SerializedName("results") @Expose var results: List<MovieModel>? = null
    @SerializedName("page") @Expose var page: Int = 1
    @SerializedName("total_results") @Expose var totalResults: Int = 0
    @SerializedName("total_pages") @Expose var totalPages: Int = 1
}