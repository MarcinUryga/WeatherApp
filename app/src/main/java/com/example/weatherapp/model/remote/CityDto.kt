package com.example.weatherapp.model.remote

data class CityDto(
    val lat: Double,
    val lon: Double,
    val iso2: String,
    val name: String,
    val type: String,
    val country: String,
    val geonameID: Long,
    val population: Long
)
