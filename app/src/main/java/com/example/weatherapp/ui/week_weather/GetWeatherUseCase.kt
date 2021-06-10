package com.example.weatherapp.ui.week_weather

import com.example.weatherapp.extensions.toCelsius
import com.example.weatherapp.model.remote.DayWeatherDto
import com.example.weatherapp.model.remote.WeekDetailsDto
import com.example.weatherapp.source.Result
import com.example.weatherapp.source.WeatherRepository
import com.example.weatherapp.source.network.ApiConstants.ICON_BASE_URL
import com.example.weatherapp.model.local.DayWeather
import com.example.weatherapp.model.local.Temperature
import com.example.weatherapp.model.local.WeekWeather
import com.example.weatherapp.utils.IconUtils.PNG_EXTENSION
import io.reactivex.Single
import org.joda.time.DateTime
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    fun getWeather(): Single<Result<WeekWeather>> {
        return weatherRepository.getRemoteWeekWeather()
            .map {
                return@map when (it) {
                    is Result.Success -> Result.Success(transformToWeekWeather(it.data))
                    is Result.Loading -> it
                    is Result.Error -> it
                }
            }
    }

    private fun transformToWeekWeather(dto: WeekDetailsDto): WeekWeather {
        return WeekWeather(
            city = dto.city?.name,
            avgWeekTemperature = Temperature(
                dto.list.sumByDouble { it?.temp?.day ?: 0.0 } / dto.list.size,
                Temperature.Degree.KELVIN
            ).toCelsius(),
            days = dto.list.map { transformToDayWeather(it) }
        )
    }

    private fun transformToDayWeather(dto: DayWeatherDto?): DayWeather {
        return DayWeather(
            date = DateTime(dto?.dt?.times(1000)),
            minTemperature = Temperature(
                value = dto?.temp?.min,
                Temperature.Degree.KELVIN
            ).toCelsius(),
            maxTemperature = Temperature(
                value = dto?.temp?.max,
                Temperature.Degree.KELVIN
            ).toCelsius(),
            dayTemperature = Temperature(
                value = dto?.temp?.day,
                Temperature.Degree.KELVIN
            ).toCelsius(),
            weatherIcon = "$ICON_BASE_URL${dto?.weather?.first()?.icon}$PNG_EXTENSION",
            description = dto?.weather?.first()?.description,
            pressure = dto?.pressure,
            windSpeed = dto?.speed
        )
    }
}