package com.example.weatherapp.model.local

class Temperature(
    var value: Double?,
    var degree: Degree
) {

    override fun toString() = "$value ${degree.mark}"

    enum class Degree(val mark: String) {
        CELSIUS("°C"),
        KELVIN("°K")
    }
}