package com.example.weatherapp.ui.week_weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.EventObserver
import com.example.weatherapp.databinding.FragmentWeekWeatherBinding
import com.example.weatherapp.source.Result
import com.example.weatherapp.ui.main.MainActivity
import com.example.weatherapp.ui.week_weather.adapter.DaysWeatherAdapter
import com.example.weatherapp.model.local.DayWeather
import com.example.weatherapp.model.local.WeekWeather
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeekWeatherFragment : Fragment() {
    private val viewModel by viewModels<WeekWeatherViewModel>()
    private var _viewDataBinding: FragmentWeekWeatherBinding? = null
    private val viewDataBinding get() = _viewDataBinding!!
    private lateinit var daysWeatherAdapter: DaysWeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewDataBinding = FragmentWeekWeatherBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.viewmodel = viewModel
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupAdapter()
        observeLiveData()
    }

    private fun setupAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            daysWeatherAdapter = DaysWeatherAdapter(viewModel)
            viewDataBinding.recyclerView.adapter = daysWeatherAdapter
        } else {
            Log.d(TAG, "ViewModel not initialized when attempting to set up adapter.")
        }
    }

    private fun observeLiveData() {
        viewModel.weather.observe(viewLifecycleOwner, {
            if (it is Result.Success && it.data.days.isNotEmpty()) {
                updateToolbar(it.data)
                daysWeatherAdapter.submitList(it.data.days)
            }
        })

        viewModel.openDetailsEvent.observe(viewLifecycleOwner, EventObserver {
            openDetails(it)
        })
    }

    private fun openDetails(weather: DayWeather) {
        val action = WeekWeatherFragmentDirections.openDayWeatherFragment(weather)
            .setCity(viewModel.cityName)
        findNavController().navigate(action)
    }

    private fun updateToolbar(data: WeekWeather) {
        (requireActivity() as MainActivity).supportActionBar?.title =
            "${data.city}, ${data.avgWeekTemperature}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewDataBinding.recyclerView.adapter = null
        _viewDataBinding = null
    }


    companion object {
        private const val TAG = "WeekWeatherFragment"
    }
}