package com.example.study_jetpack_compose

import com.example.study_jetpack_compose.RetrofitInstance.api

class WeatherRepository() {
    suspend fun getWeather(cityName: String, apiKey: String): WeatherResponse {
        return api.getWeather(cityName, apiKey)
    }
}