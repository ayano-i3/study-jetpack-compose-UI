package com.example.study_jetpack_compose

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>,
    val name: String,
    val temp: Float,
    val icon: String,
    val description: String,
    val time: String,
)


data class Main(
    val temp: Float,
    val humidity: Int,
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


data class HourlyWeatherData(
    val temperature: String,
    val precipitation: String,
    val iconResId: Int,
    val weather: String,
    val time: String

)

val weatherList = listOf(
    HourlyWeatherData("25℃", "50%", R.drawable.ic_cloudy, "曇り", "現在"),
    HourlyWeatherData("26℃", "80%", R.drawable.ic_cloudy, "曇り", "16:00"),
    HourlyWeatherData("24℃", "90%",R.drawable.ic_rainy, "雨", "17:00"),
    HourlyWeatherData("26℃", "70%", R.drawable.ic_cloudy, "曇り", "18:00"),
    HourlyWeatherData("24℃", "90%", R.drawable.ic_rainy, "雨", "19:00"),
    HourlyWeatherData("22℃", "85%", R.drawable.ic_rainy, "雨", "20:00"),
    HourlyWeatherData("24℃", "50%", R.drawable.ic_cloudy, "曇り", "21:00"),
    HourlyWeatherData("28℃", "30%", R.drawable.ic_sunny, "晴れ", "22:00"),
    HourlyWeatherData("27℃", "30%", R.drawable.ic_sunny, "晴れ", "23:00"),
    HourlyWeatherData("25℃", "50%", R.drawable.ic_cloudy, "曇り", "24:00")
)

data class DailyWeatherData(
    val day: String,
    val precipitation: String,
    val iconResId: Int,
    val temperatureHigh: String,
    val temperatureLow: String
)

val dailyWeatherList = listOf(
    DailyWeatherData("今日", "90%", R.drawable.ic_cloudy, "25℃", "19℃"),
    DailyWeatherData("6月28日 金曜日", "10%", R.drawable.ic_sunny, "27℃", "18℃"),
    DailyWeatherData("6月29日 土曜日", "20%", R.drawable.ic_rainy, "22℃", "16℃"),
    DailyWeatherData("6月30日 日曜日", "30%", R.drawable.ic_cloudy, "24℃", "17℃"),
    DailyWeatherData("7月1日 月曜日", "40%", R.drawable.ic_sunny, "28℃", "20℃"),
    DailyWeatherData("7月2日 火曜日", "50%", R.drawable.ic_rainy, "23℃", "18℃"),
    DailyWeatherData("7月3日 水曜日", "60%", R.drawable.ic_cloudy, "26℃", "19℃"),
    DailyWeatherData("7月4日 木曜日", "70%", R.drawable.ic_sunny, "29℃", "21℃"),
    DailyWeatherData("7月5日 金曜日", "80%", R.drawable.ic_rainy, "24℃", "18℃"),
    DailyWeatherData("7月6日 土曜日", "90%", R.drawable.ic_cloudy, "25℃", "19℃")
)
