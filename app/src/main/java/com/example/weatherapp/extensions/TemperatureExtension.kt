package com.example.weatherapp.extensions

import com.example.weatherapp.model.local.Temperature

fun Temperature.toCelsius(): Temperature {
    if (degree == Temperature.Degree.KELVIN && value != null) {
        value = (value!! - 273.15).roundTo(2)
        degree = Temperature.Degree.CELSIUS
    }
    return this
}

fun Temperature.toKelvin(): Temperature {
    if (degree == Temperature.Degree.CELSIUS && value != null) {
        value = (value!! + 273.15).roundTo(2)
        degree = Temperature.Degree.KELVIN
    }
    return this
}