package com.example.study_jetpack_compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun TenDayWeatherForecast(dailyWeatherList: List<DailyWeatherData>) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "10日間の天気予報",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    color = Color.Blue.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(16.dp)

        ) {
            items(dailyWeatherList) { dailyWeatherData ->
                DailyWeatherItem(dailyWeatherData = dailyWeatherData)
            }
        }
    }
}

@Composable
fun DailyWeatherItem(dailyWeatherData: DailyWeatherData) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),

        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = dailyWeatherData.day,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = dailyWeatherData.precipitation,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(0.5f)
        )
        Image(
            painter = painterResource(id = dailyWeatherData.iconResId),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .weight(0.5f)
        )
        Text(
            text = "${dailyWeatherData.temperatureHigh} / ${dailyWeatherData.temperatureLow}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(0.5f)
        )
    }
}
