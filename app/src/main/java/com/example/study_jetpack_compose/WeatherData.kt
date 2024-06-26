package com.example.study_jetpack_compose

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>,
    val name: String,
    @SerializedName("hourly") val hourlyWeather: List<HourlyWeather>,
    val temp: Float,
    val icon: String,
    val description: String,
    val time: String,
)


data class Main(
    val temp: Float,
    @SerializedName("feels_like") val feelsLike: Float,
    @SerializedName("temp_min") val tempMin: Float,
    @SerializedName("temp_max") val tempMax: Float,
)

data class Weather(
    val main: String,
    val description: String,
    val icon: String,
    val pop: Int,
    val temp: Float,
    val time: String
)

data class DailyForecastResponse(
    val daily: List<DailyWeather>
)

data class DailyWeather(
    val dt: Long,
    val temp: Temp,
    val weather: List<Weather>,
    val pop: Float
)
data class Temp(
    val day: Float,
    val min: Float,
    val max: Float
)


data class HourlyWeather(
    val temp: Float,
    val pop: Int, // 降水確率
    val icon: String,
    val description: String,
    val time: String
)