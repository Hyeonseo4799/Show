package com.project.data.repository

import com.project.model.*
import kotlinx.coroutines.flow.Flow

interface ResultRepository {
    fun getShows(query: String): Flow<List<Show>>
}