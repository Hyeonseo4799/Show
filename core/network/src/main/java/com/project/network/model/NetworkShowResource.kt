package com.project.network.model

data class NetworkShowResource(
    val score: Double,
    val show: Show
) {
    data class Show(
        val averageRuntime: Int,
        val dvdCountry: Any,
        val ended: String,
        val externals: Externals,
        val genres: List<String>,
        val id: Int,
        val image: Image,
        val language: String,
        val links: Links,
        val name: String,
        val network: Network,
        val officialSite: String,
        val premiered: String,
        val rating: Rating,
        val runtime: Int,
        val schedule: Schedule,
        val status: String,
        val summary: String,
        val type: String,
        val updated: Int,
        val url: String,
        val webChannel: WebChannel,
        val weight: Int
    ) {
        data class Externals(
            val imdb: String,
            val thetvdb: Int,
            val tvrage: Int
        )

        data class Image(
            val medium: String,
            val original: String
        )

        data class Links(
            val nextepisode: Nextepisode,
            val previousepisode: Previousepisode,
            val self: Self
        ) {
            data class Nextepisode(
                val href: String
            )

            data class Previousepisode(
                val href: String
            )

            data class Self(
                val href: String
            )
        }

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

        data class Rating(
            val average: Double
        )

        data class Schedule(
            val days: List<String>,
            val time: String
        )

        data class WebChannel(
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
    }
}
