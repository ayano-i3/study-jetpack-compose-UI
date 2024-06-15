package com.example.study_jetpack_compose

class WeatherRepository {
    suspend fun getWeather(cityName: String, apiKey: String): WeatherResponse {
        return RetrofitInstance.api.getWeather(cityName, apiKey)
    }
}