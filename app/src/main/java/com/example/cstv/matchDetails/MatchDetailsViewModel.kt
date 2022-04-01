package com.example.cstv.matchDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cstv.entities.TeamsItem
import com.example.cstv.service.RetrofitClient
import com.example.cstv.service.listeners.TeamsListeners
import com.example.cstv.service.repository.RepositoryTeams

class MatchDetailsViewModel: ViewModel() {

    private val mRepositoryTeams = RepositoryTeams()

    private var mTeamsList = MutableLiveData<List<TeamsItem>>()
    var teamsList = mTeamsList

    var teamNames: String = ""

    private lateinit var errorMessage: String

    fun listTeams() {

        mRepositoryTeams.listTeams(
            RetrofitClient.TOKEN,
            teamNames,
            object : TeamsListeners {

                override fun onSucces(entity: List<TeamsItem>) {
                    teamsList.value = entity
                }

                override fun onFailure(message: String) {
                    errorMessage = message
                }

            })


    }
}