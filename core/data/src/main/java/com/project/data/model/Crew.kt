package com.project.data.model

import com.project.model.Crew
import com.project.network.model.NetworkCrew

fun NetworkCrew.toCrew() = Crew(
    birthday = person.birthday,
    country = person.country,
    deathday = person.deathday,
    gender = person.gender,
    image = person.image,
    link = person.links.self.href,
    name = person.name
)