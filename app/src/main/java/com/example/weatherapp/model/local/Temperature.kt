package com.example.weatherapp.model.local

import java.io.Serializable

class Temperature(
    var value: Double?,
    var degree: Degree
) : Serializable {

    override fun toString() = "$value ${degree.mark}"

    enum class Degree(val mark: String) {
        CELSIUS("°C"),
        KELVIN("°K")
    }
}