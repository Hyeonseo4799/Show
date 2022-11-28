package com.project.data.repository

import com.project.data.model.*
import com.project.network.TVMazeDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ResultRepositoryImpl @Inject constructor(
    private val tvMazeDataSource: TVMazeDataSource
) : ResultRepository {
    override fun getShows(query: String): Flow<List<com.project.model.Show>> = flow {
        emit(tvMazeDataSource.getShows(query).map { it.toShow() })
    }
}