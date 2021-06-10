package com.example.weatherapp.source

import android.util.Log
import com.example.weatherapp.model.remote.WeekDetailsDto
import com.example.weatherapp.source.network.WeatherApiService
import com.example.weatherapp.model.local.Temperature.Degree.*
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val taskApi: WeatherApiService,
) {
    fun getRemoteWeekWeather(): Single<Result<WeekDetailsDto>> {
        return taskApi.getWeekWeather()
            .onErrorReturn { throwable ->
                Log.e(TAG, "getWeather", throwable)
                return@onErrorReturn null
            }
            .map { transformToResult(it) }
    }

    private fun transformToResult(weekDetailsDto: WeekDetailsDto?): Result<WeekDetailsDto> {
        return weekDetailsDto?.let {
            Result.Success(it)
        } ?: run {
            Result.Error(Exception("Error during getting weather for week"))
        }
    }

    companion object {
        private val TAG = WeatherRepository::class.simpleName
    }
}