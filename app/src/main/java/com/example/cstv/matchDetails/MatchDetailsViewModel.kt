package com.example.cstv.matchDetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cstv.entities.TeamsItem
import com.example.cstv.service.RetrofitClient
import com.example.cstv.service.listeners.TeamsListeners
import com.example.cstv.service.repository.RepositoryTeams

class MatchDetailsViewModel: ViewModel() {

    private val mRepositoryTeams = RepositoryTeams()

    private var mTeam1 = MutableLiveData<TeamsItem>()
    var team1 = mTeam1

    private var mTeam2 = MutableLiveData<TeamsItem?>()
    var team2 = mTeam2

    private lateinit var errorMessage: String

    fun listTeams(teamNames: String) {

        mRepositoryTeams.listTeams(
            RetrofitClient.TOKEN,
            teamNames,
            object : TeamsListeners {

                override fun onSucces(entity: List<TeamsItem>) {
                    team1.value = entity[0]
                    if(entity.size == 2) {
                        team2.value = entity[1]
                    }else{
                        team2.value = null
                    }
                }

                override fun onFailure(message: String) {
                    errorMessage = message
                    Log.e("MatchDetails", "onFailure: $errorMessage", )
                }

            })


    }
}