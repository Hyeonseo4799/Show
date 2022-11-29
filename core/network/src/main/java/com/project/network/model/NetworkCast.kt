package com.project.network.model

import com.project.model.Country

data class NetworkCast(
    val character: Character?,
    val person: Person?,
    val self: Boolean?,
    val voice: Boolean?
) {
    data class Character(
        val id: Int,
        val image: Any,
        val links: Links,
        val name: String,
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
        data class Image(
            val medium: String,
            val original: String
        )

        data class Links(
            val self: Self
        ) {
            data class Self(
                val href: String
            )
        }
    }
}
