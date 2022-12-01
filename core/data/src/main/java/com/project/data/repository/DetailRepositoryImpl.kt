package com.project.data.repository

import com.project.data.model.toCrew
import com.project.data.model.toShowResource
import com.project.model.Cast
import com.project.model.ShowResource
import com.project.network.TVMazeDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val tvMazeDataSource: TVMazeDataSource
) : DetailRepository {
    override fun getCrews(id: Int): Flow<List<Cast>> = flow {
        emit(tvMazeDataSource.getCrews(id).map { it.toCrew() })
    }

    override fun getShowResources(id: Int): Flow<ShowResource> = flow {
        emit(tvMazeDataSource.getShowResources(id).toShowResource())
    }
}