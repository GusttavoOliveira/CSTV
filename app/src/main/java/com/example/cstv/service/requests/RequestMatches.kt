package com.example.cstv.service.requests

import com.example.cstv.entities.Match
import com.example.cstv.entities.MatchItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RequestMatches {

    @GET("csgo/matches/running")
    fun listRunningMatches(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("per_page") numberCards: Int
    ): Call<MutableList<MatchItem>>

    @GET("csgo/matches/running/upcoming")
    fun listUpcomingMatches(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("per_page") numberCards: Int
    ): Call<MutableList<MatchItem>>
}