package com.example.weatherapp.ui.week_weather

import androidx.lifecycle.*
import com.example.weatherapp.Event
import com.example.weatherapp.model.local.DayWeather
import com.example.weatherapp.model.local.WeekWeather
import com.example.weatherapp.source.Result
import com.example.weatherapp.utils.NetworkConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class WeekWeatherViewModel @Inject constructor(
    private val networkConnection: NetworkConnection,
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _forceRefresh = MutableLiveData(false)
    var forceRefresh: LiveData<Boolean> = _forceRefresh

    private var _weather: MediatorLiveData<Result<WeekWeather>> = MediatorLiveData()
    var weather: LiveData<Result<WeekWeather>> = _weather

    private val _openDetailsEvent = MutableLiveData<Event<DayWeather>>()
    var openDetailsEvent: LiveData<Event<DayWeather>> = _openDetailsEvent

    var cityName: String? = ""

    init {
        updateWeather()
    }

    fun updateWeather() {
        if (networkConnection.isConnected()) {
            _weather = getWeather()
        } else {
            _forceRefresh.value = false
            _weather.value = Result.Error(Exception("No internet"))
        }
    }

    private fun getWeather(): MediatorLiveData<Result<WeekWeather>> {
        _weather.value = Result.Loading
        val weatherSource = getWeatherSource()
        _weather.addSource(weatherSource) {
            _weather.value = it
            _weather.removeSource(weatherSource)
        }
        return _weather
    }

    private fun getWeatherSource(): LiveData<Result<WeekWeather>> {
        return LiveDataReactiveStreams.fromPublisher(
            getWeatherUseCase.getWeather()
                .doFinally { _forceRefresh.postValue(false) }
                .toFlowable()
                .onErrorReturn { Result.Error(Exception("error during getting weather")) }
                .doOnNext { if (it is Result.Success) cityName = it.data.city }
                .subscribeOn(Schedulers.io())
        )
    }

    fun openDetails(dayWeather: DayWeather) {
        _openDetailsEvent.value = Event(dayWeather)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    companion object {
        private const val TAG = "WeekWeatherViewModel"
    }
}