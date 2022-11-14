package com.project.network

import com.project.network.model.NetworkCrew
import com.project.network.model.NetworkShowResource

interface TVMazeDataSource {
    suspend fun getShowResources(query: String): List<NetworkShowResource>

    suspend fun getCrews(id: Int): List<NetworkCrew>
}