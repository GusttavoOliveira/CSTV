package com.example.cstv.util

import android.util.Log
import java.sql.Time
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.time.Duration.Companion.days

class FormattedDate(date: String) {

    private val current = Date()
    private val c = Calendar.getInstance()

    private val year = date.split("-","T",":","Z")[0]
    private val month = date.split("-","T",":","Z")[1]
    private val day = date.split("-","T",":","Z")[2]
    private val hour = date.split("-","T",":","Z")[3]
    private val minutes = date.split("-","T",":","Z")[4]

    private var formatter = SimpleDateFormat("EEEE")
    private var newDate = Date(year.toInt(), month.toInt(), day.toInt())
    private var formattedDate = formatter.format(newDate)


    fun dateInformation() : String{
        c.time = current
        val hourInt = hour.toInt() - 3 //Fuso horário
        val dayOfMonth = c.get(Calendar.DAY_OF_MONTH)
        Log.d("dateInformation", "dateInformation: ${current.time}")
        formattedDate = translate()

        if(day.toInt() <= dayOfMonth){
            return "Hoje, $hourInt:$minutes"
        }else {
            return "${formattedDate}, $hour:$minutes"
        }
    }

    private fun translate(): String{

        /**
         * Através de testes constatei empiricamente alteração no nome dos dias
         * da semana quando usada a classe Date() passando atributos no construtor
        **/
        return when(formattedDate){
            "Monday" -> "Sex"
            "Tuesday" -> "Sáb"
            "Wednesday" -> "Dom"
            "Thursday" -> "Seg"
            "Friday" -> "Ter"
            "Saturday" -> "Qua"
            else -> "Qui"
        }
    }
}