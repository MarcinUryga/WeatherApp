package com.example.recruitmentapp.utils.binding_adapters

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.weatherapp.R
import com.example.weatherapp.extensions.fullDate
import com.example.weatherapp.extensions.shortDate
import com.example.weatherapp.source.Result
import com.example.weatherapp.model.local.Temperature
import org.joda.time.DateTime

@BindingAdapter("showOnError")
fun TextView.showError(result: Result<Any>?) {
    visibility = if (result is Result.Error) {
        text = result.exception.message
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("shortDate")
fun TextView.showShortDate(date: DateTime?) {
    date?.let {
        text = it.shortDate()
    } ?: run {
        visibility = View.GONE
    }
}

@BindingAdapter("dayOfWeekDate")
fun TextView.showDayOfWeekDate(date: DateTime?) {
    date?.let {
        text = String.format(
            resources.getString(R.string.day_of_week_date),
            it.dayOfWeek().asText,
            it.fullDate()
        )
    } ?: run {
        visibility = View.GONE
    }
}

@BindingAdapter("temperature")
fun TextView.showTemperature(temp: Temperature?) {
    temp?.let {
        text = String.format(
            resources.getString(R.string.temperature_value),
            it.value,
            it.degree.mark
        )
    } ?: run {
        visibility = View.GONE
    }
}

@BindingAdapter("pressure")
fun TextView.showPressure(pressure: Double?) {
    pressure?.let {
        text = String.format(
            resources.getString(R.string.pressure_value),
            it
        )
    } ?: run {
        visibility = View.GONE
    }
}

@BindingAdapter("windSpeed")
fun TextView.showWindSpeed(windSpeed: Double?) {
    windSpeed?.let {
        text = String.format(
            resources.getString(R.string.speed_value),
            it
        )
    } ?: run {
        visibility = View.GONE
    }
}