package com.example.cstv.service.listeners

import com.example.cstv.entities.MatchItem

interface MatchesListeners {

     fun onSuccesRunning(entityRunning: MutableList<MatchItem>)

     fun onSuccesUpcoming(entityUpcoming: MutableList<MatchItem>)

     fun onFailure(message: String)

}