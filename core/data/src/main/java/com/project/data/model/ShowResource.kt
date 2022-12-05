package com.project.data.model

import com.project.model.ShowResource
import com.project.network.model.NetworkShowResource

fun NetworkShowResource.toShowResource() = ShowResource(
    officialSite = officialSite,
    langueage = language,
    genres = genres,
    schedule = schedule,
    status = status,
    averageRuntime = averageRuntime,
    premiered = premiered,
    ended = ended,
    image = image?.original,
    summary = summary,
    name = name
)