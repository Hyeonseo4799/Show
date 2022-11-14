package com.project.data.model

import com.project.model.ShowResource
import com.project.network.model.NetworkShowResource

fun NetworkShowResource.toShowResource() = ShowResource(
    id = show.id,
    name = show.name,
    type = show.type,
    language = show.language,
    genres = show.genres,
    status = show.status,
    averageRuntime = show.averageRuntime,
    runtime = show.runtime,
    premiered = show.premiered,
    ended = show.ended,
    officialSite = show.officialSite,
    schedule = show.schedule,
    rating = show.rating.average,
    network = show.network,
    image = show.image,
    summary = show.summary
)