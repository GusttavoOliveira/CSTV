package com.example.cstv.service.requests

import com.example.cstv.entities.MatchItem
import com.example.cstv.entities.TeamsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RequestTeams {

    //O valor de teamName deve ser uma string contendo os nomes dos times
    //usando comma-separate como: "MAD Lions,BlackBelt"

    @GET("csgo/teams")
    fun listTeams(
        @Header("Authorization") token: String,
        @Query("filter[name]") teamNames: String,
    ): Call<List<TeamsItem>>
}