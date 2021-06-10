package com.example.weatherapp.utils

import com.example.weatherapp.extensions.localize
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

object DateTimeFormatters {
    private const val FULL_DATE_FORMAT = "dd-MM-yyyy"

    private val defaultFormatter by lazy {
        DateTimeFormat.forPattern(FULL_DATE_FORMAT).localize()
    }

    private val shortDateFormatter by lazy {
        DateTimeFormat.shortDate().localize()
    }

    fun fullDate(date: DateTime): String {
        return defaultFormatter.print(date)
    }

    fun shortDate(dateTime: DateTime): String {
        return shortDateFormatter.print(dateTime)
    }
}