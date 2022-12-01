package com.project.network

import com.project.network.model.NetworkCast
import com.project.network.model.NetworkShow
import com.project.network.model.NetworkShowResource

interface TVMazeDataSource {
    suspend fun getShows(query: String): List<NetworkShow>

    suspend fun getCrews(id: Int): List<NetworkCast>

    suspend fun getShowResources(id: Int): NetworkShowResource
}