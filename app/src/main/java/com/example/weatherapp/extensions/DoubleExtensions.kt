package com.example.weatherapp.extensions

fun Double.roundTo(n: Int): Double {
    return "%.${n}f".format(this).toDouble()
}