package com.example.cstv.entities

import com.google.gson.annotations.SerializedName

data class Serie(
    val begin_at: String,
    val description: Any,
    val end_at: String,
    val full_name: String,
    @SerializedName("id") val serieId: Int,
    val league_id: Int,
    val modified_at: String,
    @SerializedName("name") val serieName: String,
    val season: String,
    val slug: String,
    val tier: String,
    val winner_id: Any,
    val winner_type: Any,
    val year: Int
)