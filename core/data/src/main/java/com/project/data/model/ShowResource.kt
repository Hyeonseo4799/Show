package com.project.data.model

import com.project.network.model.NetworkShowResource.Show.*

data class ShowResource(
    val id: Int,
    val name: String,
    val type: String,
    val language: String,
    val genres: List<String>,
    val status: String,
    val averageRuntime: Int,
    val runtime: Int,
    val premiered: String,
    val ended: String,
    val officialSite: String,
    val schedule: Schedule,
    val rating: Double,
    val network: Network,
    val image: Image,
    val summary: String,
)
