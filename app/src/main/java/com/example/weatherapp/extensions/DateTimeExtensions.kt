package com.example.weatherapp.extensions

import com.example.weatherapp.utils.DateTimeFormatters
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatter

fun DateTimeFormatter.localize(): DateTimeFormatter {
    return this.withLocale(locale)
}

fun DateTime.shortDate(): String {
    return DateTimeFormatters.shortDate(this)
}

fun DateTime.fullDate(): String {
    return DateTimeFormatters.fullDate(this)
}