package com.example.weatherapp.source.network

import com.example.weatherapp.model.remote.WeekDetailsDto
import io.reactivex.Single
import retrofit2.http.GET

interface WeatherApiService {
    @GET("d0f4f1a8f982f8e0350f")
    fun getWeekWeather(): Single<WeekDetailsDto>
}