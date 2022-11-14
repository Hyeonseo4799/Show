package com.project.data.repository

import com.project.data.model.Crew
import com.project.data.model.ShowResource
import com.project.network.TVMazeDataSource
import com.project.network.model.toCrew
import com.project.network.model.toShowResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TVMazeRepositoryImpl(
    private val tvMazeDataSource: TVMazeDataSource
) : TVMazeRepository {
    override fun getShowResources(query: String): Flow<List<ShowResource>> = flow {
        emit(tvMazeDataSource.getShowResources(query).map { it.toShowResource() })
    }

    override fun getCrews(id: Int): Flow<List<Crew>> = flow {
        emit(tvMazeDataSource.getCrews(id).map { it.toCrew() })
    }
}