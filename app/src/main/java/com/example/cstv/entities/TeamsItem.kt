package com.example.cstv.entities

import com.google.gson.annotations.SerializedName

data class TeamsItem(
    val acronym: Any,
    val current_videogame: CurrentVideogame,
    @SerializedName("id") val teamId: Int,
    @SerializedName("image_url") val teamImageUrl: String,
    val location: String,
    val modified_at: String,
    @SerializedName("name") val teamName: String,
    val players: List<Player?>,
    val slug: String
)