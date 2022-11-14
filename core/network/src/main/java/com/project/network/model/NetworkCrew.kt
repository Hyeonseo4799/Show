package com.project.network.model

import com.project.model.*

data class NetworkCrew(
    val person: Person,
    val type: String
) {
    data class Person(
        val birthday: String,
        val country: Country,
        val deathday: Any,
        val gender: String,
        val id: Int,
        val image: Image,
        val links: Links,
        val name: String,
        val updated: Int,
        val url: String
    ) {
        data class Links(
            val self: Self
        ) {
            data class Self(
                val href: String
            )
        }
    }
}
