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


//    suspend fun listMatches(token: String, page: Int, numberCards: Int, apiListeners: MatchesListeners) {
//
//        val callRunning: Call<MutableList<MatchItem>> =
//            mRemote.listRunningMatches(token, page, numberCards)
//
//        val callUpcoming: Call<MutableList<MatchItem>> =
//            mRemote.listUpcomingMatches(token, page, numberCards)
//
//        lateinit var runningList: MutableList<MatchItem>
//        lateinit var upcomingList: MutableList<MatchItem>
//
//        //Chamada assíncrona running matches
//        callRunning.enqueue(object : Callback<MutableList<MatchItem>> {
//
//            override fun onResponse(
//                call: Call<MutableList<MatchItem>>,
//                response: Response<MutableList<MatchItem>>
//            ) {
//                response.body()?.let {
//
//                    it.forEach { running ->
//                        running.is_live = true
//                    }
//
//                    runningList = it
//                    apiListeners.onSuccesRunning(runningList)
//                }
//
//            }
//
//            override suspend fun onFailure(call: Call<MutableList<MatchItem>>, t: Throwable) {
//                apiListeners.onFailure(t.message.toString())
//            }
//
//        })
//
//
//        //Chamada assíncrona running matches
//        callUpcoming.enqueue(object : Callback<MutableList<MatchItem>> {
//
//            override fun onResponse(
//                call: Call<MutableList<MatchItem>>,
//                response: Response<MutableList<MatchItem>>
//            ) {
//                response.body()?.let {
//
//                    it.forEach { upcoming ->
//                        upcoming.is_live = false
//                    }
//
//                    upcomingList = it
//                    apiListeners.onSuccesUpcoming(upcomingList)
//                }
//
//
//            }
//
//            override fun onFailure(call: Call<MutableList<MatchItem>>, t: Throwable) {
//                apiListeners.onFailure(t.message.toString())
//            }
//
//        })
//
//    }

    suspend fun getListMatches(
        token: String,
        page: Int,
        numberCards: Int,
        ): List<MatchItem>? = withContext(Dispatchers.Default){
            try {
                Log.d("requisicao", "getListMatches: ENTREI NO TRY")
                mutableListApiState.value = ApiState.Loading

                val runningResponse = mRemote.listRunningMatches(token, page, numberCards)
                Log.d("requisicao", "getListMatches: FIZ REQUISIÇÃO 1")
                val upcomingResponse = mRemote.listUpcomingMatches(token, page, numberCards)
                Log.d("requisicao", "getListMatches: FIZ REQUISIÇÃO 2")

                mutableListApiState.value = ApiState.Succes
                Log.d("requisicao", "getListMatches: SUCESSO")

                runningResponse + upcomingResponse
            }catch (e: Exception){
                mutableListApiState.value = ApiState.Failed
                Log.d("TAG", "ERRO: $e")
                null
            }

    }

}
