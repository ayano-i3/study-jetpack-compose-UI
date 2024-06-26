package com.example.study_jetpack_compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun WeatherInfoScreen(weatherResponse: WeatherResponse) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(text = "現在の天気", style = MaterialTheme.typography.titleLarge)
                Text(
                    text = "${String.format("%.0f", weatherResponse.main.temp)}°C",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.weight(1f)
            ) {
                val iconUrl =
                    "https://openweathermap.org/img/wn/${weatherResponse.weather[0].icon}@2x.png"
                Image(
                    painter = rememberAsyncImagePainter(iconUrl),
                    contentDescription = weatherResponse.weather[0].description,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = weatherResponse.weather[0].description,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "最高気温: ${String.format("%.0f", weatherResponse.main.tempMin)}°C・最低気温: ${String.format("%.0f", weatherResponse.main.tempMax)}°C",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "体感温度: ${String.format("%.0f", weatherResponse.main.feelsLike)}°C",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun HourlyWeatherSession(hourlyWeatherList: List<HourlyWeather>) {
    Column {
        Text(
            text = "1時間ごとの天気予報",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp)
        )

        LazyRow(modifier = Modifier.padding(16.dp)) {
            items(hourlyWeatherList) { hourlyWeather ->
                WeatherItem(hourlyWeather)
            }
        }
    }
}

@Composable
fun WeatherItem(hourlyWeather: HourlyWeather) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = "${String.format("%.0f", hourlyWeather.temp)}°C",
            style = MaterialTheme.typography.bodyMedium
        )
        Image(
            painter = rememberAsyncImagePainter("http://openweathermap.org/img/wn/${hourlyWeather.icon}.png"),
            contentDescription = hourlyWeather.description,
            modifier = Modifier.size(40.dp)
        )
//        Text(text = "${hourlyWeather.pop}%", style = MaterialTheme.typography.bodyMedium)
//        Text(text = hourlyWeather.time, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherInfoScreenPreview() {
    WeatherInfoScreen(
        WeatherResponse(
            Main(25.0f, 25.0f, 25.0f, 25.0f),
            listOf(Weather("Clear", "clear sky", "01d", 0, 25.0f, "2021-09-01 12:00:00")),
            "Tokyo",
            listOf(HourlyWeather(25.0f, 0, "01d", "clear sky", "2021-09-01 12:00:00")),
            25.0f,
            0,
            "01d",
            "clear sky",
            "2021-09-01 12:00:00"
        )
    )
}

@Preview(showBackground = true)
@Composable
fun HourlyWeatherSessionPreview() {
    HourlyWeatherSession(
        listOf(
            HourlyWeather(25.0f, 0, "01d", "clear sky", "2021-09-01 12:00:00"),
            HourlyWeather(25.0f, 0, "01d", "clear sky", "2021-09-01 12:00:00"),
            HourlyWeather(25.0f, 0, "01d", "clear sky", "2021-09-01 12:00:00"),
            HourlyWeather(25.0f, 0, "01d", "clear sky", "2021-09-01 12:00:00"),
            HourlyWeather(25.0f, 0, "01d", "clear sky", "2021-09-01 12:00:00"),
            HourlyWeather(25.0f, 0, "01d", "clear sky", "2021-09-01 12:00:00"),
            HourlyWeather(25.0f, 0, "01d", "clear sky", "2021-09-01 12:00:00"),
            HourlyWeather(25.0f, 0, "01d", "clear sky", "2021-09-01 12:00:00"),
            HourlyWeather(25.0f, 0, "01d", "clear sky", "2021-09-01 12:00:00"),
        )
    )
}