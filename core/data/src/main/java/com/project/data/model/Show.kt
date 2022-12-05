package com.project.data.model

import com.project.model.Show
import com.project.network.model.NetworkShow

fun NetworkShow.toShow() = Show(
    id = show.id,
    name = show.name,
    image = show.image
)