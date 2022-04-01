package com.example.cstv.entities

import com.google.gson.annotations.SerializedName

data class Player(
    val age: Int,
    val birth_year: Int,
    val birthday: String,
    val first_name: String,
    val hometown: String,
    @SerializedName("id") val playerId: Int,
    @SerializedName("image_url") val playerImage: String,
    val last_name: String,
    @SerializedName("name") val nickName: String,
    val nationality: String,
    val role: Any,
    val slug: String
)