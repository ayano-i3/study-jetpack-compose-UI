package com.example.study_jetpack_compose

data class WeatherResponse(
    val main: Main,
    val name: String
)

data class Main(
    val temp: Float
)
