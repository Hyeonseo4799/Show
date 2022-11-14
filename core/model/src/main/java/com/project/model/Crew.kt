package com.project.model

data class Crew(
    val birthday: String,
    val country: Country,
    val deathday: Any,
    val gender: String,
    val image: Image,
    val link: String,
    val name: String,
)

data class Country(
    val code: String,
    val name: String,
    val timezone: String
)

data class Image(
    val medium: String,
    val original: String
)
