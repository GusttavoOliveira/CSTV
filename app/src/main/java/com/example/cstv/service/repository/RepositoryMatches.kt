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

        val callRunning: Call<MutableList<MatchItem>> =
            mRemote.listRunningMatches(token, page, numberCards)

        val callUpcoming: Call<MutableList<MatchItem>> =
            mRemote.listUpcomingMatches(token, page, numberCards)

        lateinit var runningList: MutableList<MatchItem>
        lateinit var upcomingList: MutableList<MatchItem>

        //Chamada assíncrona running matches
        callRunning.enqueue(object : Callback<MutableList<MatchItem>> {

            override fun onResponse(
                call: Call<MutableList<MatchItem>>,
                response: Response<MutableList<MatchItem>>
            ) {
                response.body()?.let {

                    it.forEach { running ->
                        running.is_live = true
                    }

                    runningList = it
                    apiListeners.onSuccesRunning(runningList)
                }

            }

            override fun onFailure(call: Call<MutableList<MatchItem>>, t: Throwable) {
                apiListeners.onFailure(t.message.toString())
            }

        })


        //Chamada assíncrona running matches
        callUpcoming.enqueue(object : Callback<MutableList<MatchItem>> {

            override fun onResponse(
                call: Call<MutableList<MatchItem>>,
                response: Response<MutableList<MatchItem>>
            ) {
                response.body()?.let {

                    it.forEach { upcoming ->
                        upcoming.is_live = false
                    }

                    upcomingList = it
                    apiListeners.onSuccesUpcoming(upcomingList)
                }


            }

            override fun onFailure(call: Call<MutableList<MatchItem>>, t: Throwable) {
                apiListeners.onFailure(t.message.toString())
            }

        })

    }
}