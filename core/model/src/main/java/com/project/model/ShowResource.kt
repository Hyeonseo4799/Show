package com.project.model

data class ShowResource(
    val officialSite: String?,
    val name: String,
    val langueage: String?,
    val genres: List<String>?,
    val schedule: Schedule,
    val status: String,
    val averageRuntime: Int?,
    val premiered: String?,
    val ended: String?,
    val image: String?,
    val summary: String?
)

data class Schedule(
    val days: List<String>,
    val time: String?
)