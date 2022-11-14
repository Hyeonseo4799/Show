package com.project.network.retrofit

import com.project.network.model.NetworkCrew
import com.project.network.model.NetworkShowResource
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVMazeApi {
    @GET("/search/shows")
    fun getShowResources(
        @Query("q") query: String
    ): List<NetworkShowResource>

    @GET("/shows/{id}/crew")
    fun getCrews(
        @Path("id") id: Int
    ): List<NetworkCrew>
}