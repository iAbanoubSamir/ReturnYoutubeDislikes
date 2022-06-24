package com.profabanoub.ryd.data.remote

import com.profabanoub.ryd.model.Video
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DataAPI {

    @GET("votes")
    suspend fun getVideoData(@Query("videoId") videoId: String): Response<Video>

}