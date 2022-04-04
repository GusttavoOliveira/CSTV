package com.example.cstv.service.repository

import com.example.cstv.entities.MatchItem
import com.example.cstv.service.listeners.MatchesListeners
import com.example.cstv.service.RetrofitClient
import com.example.cstv.service.requests.RequestMatches
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryMatches {

    private val mRemote = RetrofitClient.createService(RequestMatches::class.java)

    fun listMatches(token: String, page: Int, numberCards: Int, apiListeners: MatchesListeners) {
        val call: Call<MutableList<MatchItem>> = mRemote.listMatches(token, page, numberCards)


        //Chamada assíncrona
        call.enqueue(object : Callback<MutableList<MatchItem>> {

            override fun onResponse(
                call: Call<MutableList<MatchItem>>,
                response: Response<MutableList<MatchItem>>
            ) {
                response.body()?.let { apiListeners.onSucces(it) }
            }

            override fun onFailure(call: Call<MutableList<MatchItem>>, t: Throwable) {
                apiListeners.onFailure(t.message.toString())
            }

        })
    }
}