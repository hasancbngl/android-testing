package com.cobanogluhasan.testing.data.api

import com.cobanogluhasan.testing.data.model.ImagesResponse
import com.cobanogluhasan.testing.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {

    @GET("/api/")
    suspend fun imageSearch(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = API_KEY,
    ): Response<ImagesResponse>
}