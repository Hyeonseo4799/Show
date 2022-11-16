package com.project.data.repository

import com.project.data.model.*
import com.project.network.TVMazeDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TVMazeRepositoryImpl @Inject constructor(
    private val tvMazeDataSource: TVMazeDataSource
) : TVMazeRepository {
    override fun getShowResources(query: String): Flow<List<com.project.model.ShowResource>> = flow {
        emit(tvMazeDataSource.getShowResources(query).map { it.toShowResource() })
    }

    override fun getCrews(id: Int): Flow<List<com.project.model.Crew>> = flow {
        emit(tvMazeDataSource.getCrews(id).map { it.toCrew() })
    }
}