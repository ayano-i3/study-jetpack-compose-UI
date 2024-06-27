@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.study_jetpack_compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    val weather by viewModel.weather.observeAsState()

    Surface() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val displayText = weather?.let {
                "City: ${it.name}, Temperature: ${it.main.temp} °C"
            } ?: "Loading..."

            Text(text = displayText)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.fetchWeather("Tokyo")
    }
}


//@Preview(showBackground = true)
//@Composable
//fun WeatherAppPreview() {
//    WeatherApp()
//}
