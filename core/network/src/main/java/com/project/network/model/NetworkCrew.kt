package com.project.network.model

import com.project.data.model.Crew

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
        data class Country(
            val code: String,
            val name: String,
            val timezone: String
        )

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

fun NetworkCrew.toCrew() = Crew(
    birthday = person.birthday,
    country = person.country,
    deathday = person.deathday,
    gender = person.gender,
    image = person.image,
    link = person.links.self.href,
    name = person.name
)