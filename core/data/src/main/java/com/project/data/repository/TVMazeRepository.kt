package com.project.data.repository

import com.project.data.model.*
import kotlinx.coroutines.flow.Flow

interface TVMazeRepository {
    fun getShowResources(query: String): Flow<List<ShowResource>>

    fun getCrews(id: Int): Flow<List<Crew>>
}