package com.project.data.model

import com.project.network.model.NetworkCrew.Person.*

data class Crew(
    val birthday: String,
    val country: Country,
    val deathday: Any,
    val gender: String,
    val image: Image,
    val link: String,
    val name: String,
)
