package com.example.cstv.matchList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cstv.entities.ApiState
import com.example.cstv.entities.MatchItem
import com.example.cstv.service.listeners.MatchesListeners
import com.example.cstv.service.RetrofitClient
import com.example.cstv.service.repository.RepositoryMatches
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class MatchesListViewModel( private val mRepositoryMatches: RepositoryMatches) : ViewModel() {

    private var mMatchesList = MutableLiveData<List<MatchItem>>()
    var matchesList = mMatchesList

    private val _getApiState = MutableLiveData<ApiState>(ApiState.Initial)
    val apiState: LiveData<ApiState>
        get() = _getApiState

    private lateinit var errorMessage: String

    init {
        listMatches()
        getApiState()
    }

    private fun listMatches() {
        viewModelScope.launch {
            mMatchesList.postValue(mRepositoryMatches.getListMatches(RetrofitClient.TOKEN, 1, 50))
        }

    }


    private fun getApiState() {
        viewModelScope.launch {
            mRepositoryMatches.listApiState.collect {
                _getApiState.postValue(it)
            }
        }
    }

}