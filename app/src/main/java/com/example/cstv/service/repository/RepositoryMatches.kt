package com.example.cstv.service.repository

import android.util.Log
import com.example.cstv.entities.ApiState
import com.example.cstv.entities.MatchItem
import com.example.cstv.service.listeners.MatchesListeners
import com.example.cstv.service.RetrofitClient
import com.example.cstv.service.requests.RequestMatches
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse
import java.security.spec.ECField

class RepositoryMatches {

    private val mRemote = RetrofitClient.createService(RequestMatches::class.java)

    private val mutableListApiState = MutableStateFlow<ApiState>(ApiState.Initial)
    val listApiState : Flow<ApiState> = mutableListApiState

    suspend fun getListMatches(
        token: String,
        page: Int,
        numberCards: Int,
        ): List<MatchItem>? = withContext(Dispatchers.Default){
            try {

                mutableListApiState.value = ApiState.Loading

                var runningResponse = mRemote.listRunningMatches(token, page, numberCards)
                Log.d("requisicao", "getListMatches: FIZ REQUISIÇÃO 1")
                var upcomingResponse = mRemote.listUpcomingMatches(token, page, numberCards)
                Log.d("requisicao", "getListMatches: FIZ REQUISIÇÃO 2")

                runningResponse.forEach { it.is_live = true }
                runningResponse = runningResponse.filter { it.opponents.isNotEmpty() } as MutableList<MatchItem>
                upcomingResponse.forEach { it.is_live = false }
                upcomingResponse = upcomingResponse.filter { it.opponents.isNotEmpty() } as MutableList<MatchItem>

                mutableListApiState.value = ApiState.Succes


                runningResponse + upcomingResponse
            }catch (e: Exception){
                mutableListApiState.value = ApiState.Failed
                Log.d("TAG", "ERRO: $e")
                null
            }

    }

}
