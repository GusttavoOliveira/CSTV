package com.example.cstv.matchList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.FragmentNavigator
import com.example.cstv.entities.MatchItem
import com.example.cstv.service.listeners.APIListeners
import com.example.cstv.service.RetrofitClient
import com.example.cstv.service.repository.RepositoryMatches

class MatchesListViewModel : ViewModel() {

    private val mRepositoryMatches = RepositoryMatches()

    private var mMatchesList = MutableLiveData<MutableList<MatchItem>>()
    var matchesList = mMatchesList

    private lateinit var errorMessage: String

    fun listMatches() {

        mRepositoryMatches.listMatches(RetrofitClient.TOKEN,
            1,
            30,
            object : APIListeners {

            override fun onSucces(entity: MutableList<MatchItem>) {
                matchesList.value = entity
            }

            override fun onFailure(message: String) {
                errorMessage = message
            }

        })


    }

}