package com.example.study_jetpack_compose

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
    private val _currentWeatherData = MutableLiveData<WeatherResponse>()
    val currentWeatherData: LiveData<WeatherResponse>
        get() = _currentWeatherData

    private val apiKey = BuildConfig.MAPS_API_KEY

    private val _hourlyWeatherData = MutableLiveData<List<HourlyWeather>>()
    val hourlyWeatherData: LiveData<List<HourlyWeather>>
        get() = _hourlyWeatherData

    private val _dailyWeatherData = MutableLiveData<List<DailyWeather>>()
    val dailyWeatherData: LiveData<List<DailyWeather>>
        get() = _dailyWeatherData

    fun fetchWeather(cityName: String) {
        viewModelScope.launch {
            // ダミーデータ
//            val main = Main(25.0f)
//            val response = WeatherResponse(main, "Tokyo")
//            _weather.postValue(response)

            try {
                val response = repository.getWeather(cityName, apiKey)
                // 非同期に値を更新する
                _currentWeatherData.postValue(response)
            } catch (e: Exception) {
                // Handle error
                Log.e("WeatherViewModel", "fetchWeather: $e")
            }
        }
    }

    fun fetchDailyWeather(cityName: String) {
        viewModelScope.launch {

//            // ダミーデータ
//            val dailyWeather = listOf(
//                DailyWeather(0, Temp(20.0f, 20.0f, 30.0f), listOf(Weather("Clear", "clear sky", "01d", 0, 25.0f, "2021-09-01")), 0.0f),
//                DailyWeather(1, Temp(22.0f, 20.0f, 30.0f), listOf(Weather("Clear", "clear sky", "01d", 0, 25.0f, "2021-09-01")), 0.0f),
//                DailyWeather(2, Temp(21.0f, 20.0f, 30.0f), listOf(Weather("Clear", "clear sky", "01d", 0, 25.0f, "2021-09-01")), 0.0f),
//                DailyWeather(3, Temp(23.0f, 20.0f, 30.0f), listOf(Weather("Clear", "clear sky", "01d", 0, 25.0f, "2021-09-01")), 0.0f),
//                DailyWeather(4, Temp(24.0f, 20.0f, 30.0f), listOf(Weather("Clear", "clear sky", "01d", 0, 25.0f, "2021-09-01")), 0.0f),
//                DailyWeather(5, Temp(26.0f, 20.0f, 30.0f), listOf(Weather("Clear", "clear sky", "01d", 0, 25.0f, "2021-09-01")), 0.0f),
//                DailyWeather(6, Temp(25.0f, 20.0f, 30.0f), listOf(Weather("Clear", "clear sky", "01d", 0, 25.0f, "2021-09-01")), 0.0f),
//                DailyWeather(7, Temp(26.0f, 20.0f, 30.0f), listOf(Weather("Clear", "clear sky", "01d", 0, 25.0f, "2021-09-01")), 0.0f
//                )
//            )
//            _dailyWeatherData.postValue(dailyWeather)
            try {
                // ダミーデータの座標を使用して16日間の天気予報を取得
                val lat = 35.6895
                val lon = 139.6917
                val response = repository.getDailyWeather(lat, lon, apiKey)
                _dailyWeatherData.postValue(response.daily)
            } catch (e: Exception) {
                Log.e("WeatherViewModel", "fetchDailyWeather: $e")
            }
        }
    }

}