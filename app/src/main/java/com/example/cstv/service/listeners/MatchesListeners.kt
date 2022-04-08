package com.example.cstv.service.listeners

import com.example.cstv.entities.MatchItem

interface MatchesListeners {

     suspend fun onSucces(entityRunning: MutableList<MatchItem>, entityUpcooming: MutableList<MatchItem>)

     suspend fun onFailure(message: String)

}