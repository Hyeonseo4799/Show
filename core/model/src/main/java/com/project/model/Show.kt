package com.project.model

data class Show(
    val id: Int,
    val name: String,
    val image: Image?,
)

data class Image(
    val medium: String,
    val original: String
)