package com.project.data.repository

import com.project.model.Cast
import com.project.model.ShowResource
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun getCrews(id: Int): Flow<List<Cast>>
    fun getShowResources(id: Int): Flow<ShowResource>
}