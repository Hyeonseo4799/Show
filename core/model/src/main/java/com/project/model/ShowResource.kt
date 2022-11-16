package com.project.model

data class ShowResource(
    val id: Int,
    val name: String,
    val type: String,
    val language: String?,
    val genres: List<String>?,
    val status: String,
    val averageRuntime: Int?,
    val runtime: Int?,
    val premiered: String?,
    val ended: String?,
    val officialSite: String?,
    val schedule: Schedule?,
    val rating: Double?,
    val network: Network?,
    val image: Image?,
    val summary: String?,
)

data class Schedule(
    val days: List<String>,
    val time: String
)

data class Network(
    val country: Country,
    val id: Int,
    val name: String,
    val officialSite: String
) {
    data class Country(
        val code: String,
        val name: String,
        val timezone: String
    )
}