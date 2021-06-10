package com.example.weatherapp.model.remote

data class WeatherDto(
    val id: Long,
    val icon: String,
    val main: String,
    val description: String
)