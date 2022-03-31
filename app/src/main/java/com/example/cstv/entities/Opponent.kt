package com.example.cstv.entities

import com.google.gson.annotations.SerializedName

data class Opponent(
    val acronym: String?,
    @SerializedName("id")val teamId: Int,
    @SerializedName("image_url")val imageUrl: String?,
    val location: String?,
    val modified_at: String,
    @SerializedName("name")val teamName: String,
    val slug: String
)
