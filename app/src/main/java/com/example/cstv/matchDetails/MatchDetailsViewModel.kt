package com.example.cstv.matchDetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cstv.entities.TeamsItem
import com.example.cstv.service.RetrofitClient
import com.example.cstv.service.listeners.TeamsListeners
import com.example.cstv.service.repository.RepositoryTeams
import kotlinx.coroutines.launch

class MatchDetailsViewModel : ViewModel() {

    private val mRepositoryTeams = RepositoryTeams()

    private var mTeam1 = MutableLiveData<TeamsItem>()
    var team1 = mTeam1

    private var mTeam2 = MutableLiveData<TeamsItem?>()
    var team2 = mTeam2

    private lateinit var teams: List<TeamsItem>
    private lateinit var errorMessage: String


    fun listTeams(teamNames: String) {

        viewModelScope.launch {
            teams = mRepositoryTeams.listTeams(
                RetrofitClient.TOKEN,
                teamNames
            )!!
        }


    }
}