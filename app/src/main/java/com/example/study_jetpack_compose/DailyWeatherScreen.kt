package com.example.study_jetpack_compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DailyWeatherScreen(viewModel: WeatherViewModel) {
    val dailyWeather by viewModel.dailyWeatherData.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // style　大きいかも
        Text(text = "10日間天気予報", style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(16.dp))

        dailyWeather?.let { dailyWeatherList ->
            dailyWeatherList.forEach { dailyWeather ->
                DailyWeatherItem(dailyWeather)
            }
        } ?: run {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Loading...", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.fetchWeather("Tokyo")
        viewModel.fetchDailyWeather("Tokyo")
    }
}

@Composable
fun DailyWeatherItem(dailyWeather: DailyWeather) {
    val date = remember { SimpleDateFormat("MM/dd", Locale.getDefault()).format(Date(dailyWeather.dt * 1000)) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(text = date, modifier = Modifier.weight(1f))
        Text(text = "${String.format("%.0f", dailyWeather.pop * 100)}%", modifier = Modifier.weight(1f))
        Image(
            painter = rememberAsyncImagePainter("http://openweathermap.org/img/wn/${dailyWeather.weather[0].icon}.png"),
            contentDescription = dailyWeather.weather[0].description,
            modifier = Modifier.size(40.dp).weight(1f)
        )
        Text(text = "${String.format("%.0f", dailyWeather.temp.max)}°/${String.format("%.0f", dailyWeather.temp.min)}°", modifier = Modifier.weight(1f))
    }
}
