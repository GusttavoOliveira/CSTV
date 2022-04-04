package com.example.cstv.util

import java.text.SimpleDateFormat
import java.util.*

class FormattedDate(date: String) {

    private val current = Date()

    private val formatReceiver: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    private val formatDayName: SimpleDateFormat =  SimpleDateFormat("EEEE")
    val newDate: Date? = formatReceiver.parse(date)
    private val dayName = formatDayName.format(newDate!!)

    fun dateInformation() : String{
        if( current.year == newDate?.year || current.month == newDate?.month || current.day == newDate?.day) {

            if(current.hours == newDate.hours){
                return "AGORA"
            }

            return "Hoje, ${newDate.hours}:${newDate.minutes}"
        }else{
            return "${dayName}, ${newDate?.hours}:${newDate?.minutes}"
        }
    }

    fun isDateNow(): Boolean{
        return current == this.newDate
    }
}