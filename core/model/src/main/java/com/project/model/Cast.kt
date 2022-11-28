package com.project.model

data class Cast(
    val birthday: String?,
    val country: Country?,
    val deathday: Any?,
    val gender: String?,
    val image: String?,
    val name: String?,
)

data class Country(
    val code: String,
    val name: String,
    val timezone: String
)