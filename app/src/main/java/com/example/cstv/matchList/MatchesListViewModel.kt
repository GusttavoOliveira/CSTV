package com.example.cstv.matchList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cstv.entities.MatchItem
import com.example.cstv.service.listeners.MatchesListeners
import com.example.cstv.service.RetrofitClient
import com.example.cstv.service.repository.RepositoryMatches

class MatchesListViewModel : ViewModel() {

    private val mRepositoryMatches = RepositoryMatches()

    private var mMatchesList = MutableLiveData<List<MatchItem>>()
    var matchesList = mMatchesList

    private lateinit var errorMessage: String

    fun listMatches() {

        mRepositoryMatches.listMatches(RetrofitClient.TOKEN,
            1,
            60,
            object : MatchesListeners {

                override fun onSuccesRunning(entityRunning: MutableList<MatchItem>) {
                    Log.d("OnSuccesRunning", "$entityRunning")

                    matchesList.value =
                        matchesList.value?.plus(entityRunning.filter { it.opponents.isNotEmpty() })
                }

                override fun onSuccesUpcoming(entityUpcoming: MutableList<MatchItem>) {
                    Log.d("OnSuccesUpcoming", "$entityUpcoming")

                    matchesList.value =
                        matchesList.value?.plus(entityUpcoming.filter { it.opponents.isNotEmpty() })
                }

                override fun onFailure(message: String) {
                    errorMessage = message
                }

            })


    }

}