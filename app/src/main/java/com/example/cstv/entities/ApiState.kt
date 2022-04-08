package com.example.cstv.entities

sealed class ApiState{

    object Succes : ApiState()
    object Loading : ApiState()
    object Failed : ApiState()
    object Initial : ApiState()
}