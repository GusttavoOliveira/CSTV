package com.example.cstv.matchDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cstv.entities.ApiState
import com.example.cstv.entities.TeamsItem
import com.example.cstv.service.RetrofitClient
import com.example.cstv.service.listeners.TeamsListeners
import com.example.cstv.service.repository.RepositoryTeams
import kotlinx.coroutines.launch

class MatchDetailsViewModel(private val mRepositoryTeams: RepositoryTeams): ViewModel() {

    private val _getApiState = MutableLiveData<ApiState>(ApiState.Initial)
    val apiState: LiveData<ApiState>
        get() = _getApiState

    private var mTeam1 = MutableLiveData<TeamsItem>()
    var team1 = mTeam1

    private var mTeam2 = MutableLiveData<TeamsItem?>()
    var team2 = mTeam2

    private lateinit var teams: List<TeamsItem>
    private lateinit var errorMessage: String

    init {
        getApiState()
    }


    fun listTeams(teamNames: String) {

        viewModelScope.launch {
            teams = mRepositoryTeams.listTeams(
                RetrofitClient.TOKEN,
                teamNames
            )!!

            if (teams.size == 2) {
                mTeam1.value = teams[0]
                mTeam2.value = teams[1]

            } else if (teams.size == 1) {
                mTeam1.value = teams[0]
                mTeam2.value = null

            } else if (teams.size == 0) {
                mTeam1.value = null
                mTeam2.value = null
            }
        }
    }

    private fun getApiState() {
        viewModelScope.launch {
            mRepositoryTeams.listApiState.collect {
                _getApiState.postValue(it)
            }
        }
    }
}