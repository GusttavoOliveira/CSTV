package com.example.cstv.entities

import com.google.gson.annotations.SerializedName

data class League(
    @SerializedName("id") val leagueId: Int,
    @SerializedName("image_url") val imageUrl: String?,
    val modified_at: String,
    @SerializedName("name") val leagueName: String,
    val slug: String,
    val url: Any
)