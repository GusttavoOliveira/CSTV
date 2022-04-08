package com.example.cstv.service.repository

import com.example.cstv.entities.ApiState
import com.example.cstv.entities.TeamsItem
import com.example.cstv.service.RetrofitClient
import com.example.cstv.service.requests.RequestTeams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

class RepositoryTeams {

    private val mRemote = RetrofitClient.createService(RequestTeams::class.java)

    private val mutableListApiState = MutableStateFlow<ApiState>(ApiState.Initial)
    val listApiState : Flow<ApiState> = mutableListApiState

    suspend fun listTeams(
        token: String,
        teamNames: String
    ): List<TeamsItem>? = withContext(Dispatchers.Default){
        try {
            mutableListApiState.value = ApiState.Loading

            val teamsList = mRemote.listTeams(token, teamNames)

            mutableListApiState.value = ApiState.Succes

            teamsList
        }catch (e: Exception){
            mutableListApiState.value = ApiState.Failed
            null
        }
    }

}