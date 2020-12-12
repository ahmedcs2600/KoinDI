package com.app.koindi.models.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ActorsListResponse : BaseResponse() {

    @SerializedName("cast") @Expose var actorsList: ArrayList<ActorModel>? = null
    @SerializedName("id") @Expose var page: Int = 1

}