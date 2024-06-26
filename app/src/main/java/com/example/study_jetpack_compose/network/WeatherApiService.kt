package com.example.study_jetpack_compose.network

import com.example.study_jetpack_compose.DailyForecastResponse
import com.example.study_jetpack_compose.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApiService {
    // GETリクエストを送る
    // APIのエンドポイントを指定する
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String = "Tokyo",
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): WeatherResponse


    @GET("forecast/daily")
    suspend fun getDailyForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("cnt") days: Int = 16,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): DailyForecastResponse}
