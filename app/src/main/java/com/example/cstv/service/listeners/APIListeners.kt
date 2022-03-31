package com.example.cstv.service.listeners

import com.example.cstv.entities.MatchItem

interface APIListeners {

    fun onSucces(entity: MutableList<MatchItem>)

    fun onFailure(message: String)

}