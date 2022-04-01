package com.example.cstv.service.listeners

import com.example.cstv.entities.TeamsItem

interface TeamsListeners {

    fun onSucces(entity: List<TeamsItem>)

    fun onFailure(message: String)
}