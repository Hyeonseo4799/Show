package com.project.network.retrofit

import com.project.network.BuildConfig.BASE_URL
import com.project.network.TVMazeDataSource
import com.project.network.model.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

interface TVMazeApi {
    @GET("/search/shows")
    suspend fun getShowResources(
        @Query("q") query: String
    ): List<NetworkShowResource>

    @GET("/shows/{id}/crew")
    suspend fun getCrews(
        @Path("id") id: Int
    ): List<NetworkCrew>
}

@Singleton
class TVMaze @Inject constructor() : TVMazeDataSource {
    private val networkApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TVMazeApi::class.java)

    override suspend fun getShowResources(query: String): List<NetworkShowResource> =
        networkApi.getShowResources(query)

    override suspend fun getCrews(id: Int): List<NetworkCrew> =
        networkApi.getCrews(id)
}