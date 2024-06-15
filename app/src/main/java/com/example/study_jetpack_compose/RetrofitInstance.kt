package com.example.study_jetpack_compose

import com.example.study_jetpack_compose.network.WeatherApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/** BASE_URLを定義 */
private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

/** weatherApiServiceを返すRetrofitInstance */

object RetrofitInstance {
    val api: WeatherApiService by lazy {
        Retrofit.Builder()
            // addConverterFactoryでどのJSONライブラリを使用するか指定ができる。（Gson, Jackson, Moshi）
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(WeatherApiService::class.java)
    }
}

