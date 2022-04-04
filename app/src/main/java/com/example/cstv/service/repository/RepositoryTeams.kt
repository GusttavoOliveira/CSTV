package com.example.cstv.service.repository

import com.example.cstv.entities.TeamsItem
import com.example.cstv.service.RetrofitClient
import com.example.cstv.service.listeners.TeamsListeners
import com.example.cstv.service.requests.RequestTeams
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryTeams {

    private val mRemote = RetrofitClient.createService(RequestTeams::class.java)

    fun listTeams(token: String, teamNames: String, apiListeners: TeamsListeners) {
        val call: Call<List<TeamsItem>> = mRemote.listTeams(token, teamNames)


        //Chamada assíncrona
        call.enqueue(object : Callback<List<TeamsItem>> {

            override fun onResponse(
                call: Call<List<TeamsItem>>,
                response: Response<List<TeamsItem>>
            ) {
                response.body()?.let { apiListeners.onSucces(it) }
            }

            override fun onFailure(call: Call<List<TeamsItem>>, t: Throwable) {
                apiListeners.onFailure(t.message.toString())
            }

        })
    }

}