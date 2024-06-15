package com.example.study_jetpack_compose

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
    private val _weather = MutableLiveData<WeatherResponse>()
    val weather: LiveData<WeatherResponse>
        get() = _weather

    fun fetchWeather(cityName: String) {
        viewModelScope.launch {
            // ダミーデータ
//            val main = Main(25.0f)
//            val response = WeatherResponse(main, "Tokyo")
//            _weather.postValue(response)
            val apiKey = BuildConfig.MAPS_API_KEY
            try {
                val response = repository.getWeather(cityName, apiKey)
                // 非同期に値を更新する
                _weather.postValue(response)
            } catch (e: Exception) {
                // Handle error
                Log.e("WeatherViewModel", "fetchWeather: $e")
            }
        }
    }

}