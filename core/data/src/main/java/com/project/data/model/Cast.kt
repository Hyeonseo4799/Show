package com.project.data.model

import com.project.model.Cast
import com.project.network.model.NetworkCast

fun NetworkCast.toCrew() = Cast(
    birthday = person?.birthday,
    deathday = person?.deathday,
    country = person?.country,
    gender = person?.gender,
    image = person?.image?.original,
    name = person?.name

)
