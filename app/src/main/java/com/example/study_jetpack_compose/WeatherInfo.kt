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
import androidx.compose.ui.res.painterResource
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
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text(text = "現在の天気", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
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
                    text = translateWeatherDescription(weatherResponse.weather[0].description),
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
fun HourlyWeatherSession(weatherList: List<HourlyWeatherData>) {
    Column {
        Text(
            text = "1時間ごとの天気予報",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )

        LazyRow(modifier = Modifier.padding(8.dp)) {
            items(weatherList) { hourlyWeatherData ->
                WeatherItem(hourlyWeatherData = hourlyWeatherData)
            }
        }
    }
}

@Composable
fun WeatherItem(hourlyWeatherData: HourlyWeatherData) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = hourlyWeatherData.temperature, style = MaterialTheme.typography.bodyMedium)
        Image(
            painter = painterResource(id = hourlyWeatherData.iconResId),
            contentDescription = null
        )
        Text(text = hourlyWeatherData.weather, style = MaterialTheme.typography.bodyMedium)
        Text(text = hourlyWeatherData.precipitation, style = MaterialTheme.typography.bodySmall)
        Text(text = hourlyWeatherData.time, style = MaterialTheme.typography.bodySmall)
    }
}

    val weatherDescriptionMap = mapOf(
        "clear sky" to "晴れ",
        "few clouds" to "少し曇り",
        "scattered clouds" to "曇り",
        "broken clouds" to "曇り",
        "shower rain" to "にわか雨",
        "rain" to "雨",
        "thunderstorm" to "雷雨",
        "snow" to "雪",
        "mist" to "霧",
        "moderate rain" to "小雨",
        "overcast clouds" to "雨雲"
    )

    fun translateWeatherDescription(description: String): String {
        return weatherDescriptionMap[description] ?: description
    }



@Preview(showBackground = true)
@Composable
fun WeatherInfoScreenPreview() {
    WeatherInfoScreen(
        weatherResponse = WeatherResponse(
            main = Main(25f, 50, 25f, 25f, 25f),
            weather = listOf(Weather("clear sky", "晴れ", "01d", 0, 25f, "現在")),
            name = "東京",
            temp = 25f,
            icon = "01d",
            description = "晴れ",
            time = "現在"
        ),
    )
}
