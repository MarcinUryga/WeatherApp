package com.example.weatherapp.ui.day_weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.databinding.FragmentDayWeatherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DayWeatherFragment : Fragment() {
    private val viewModel by viewModels<DayWeatherViewModel>()
    private var _viewDataBinding: FragmentDayWeatherBinding? = null
    private val viewDataBinding get() = _viewDataBinding!!
    private val arguments: DayWeatherFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewDataBinding = FragmentDayWeatherBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.viewmodel = viewModel
        viewModel.updateDayWeather(arguments.dayWeather)
        viewModel.updateCity(arguments.city)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewDataBinding = null
    }
}