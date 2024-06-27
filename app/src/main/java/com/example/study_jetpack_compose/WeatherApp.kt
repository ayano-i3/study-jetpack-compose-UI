@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.study_jetpack_compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherApp(viewModel: WeatherViewModel) {

    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues)
            ) {
                Column {
                    SearchBar(
                        modifier = Modifier.padding(8.dp),
                        onSearch = { cityName ->
                            viewModel.fetchWeather(cityName)
                        }
                    )
                    WeatherScreen(viewModel)
                }
            }
        }
    )
}

@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    val currentWeather by viewModel.currentWeatherData.observeAsState()

    // 現在の天気情報を表示
    currentWeather?.let { weatherResponse ->
        WeatherInfoScreen(weatherResponse = weatherResponse)
    } ?: run {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Loading...", style = MaterialTheme.typography.bodyLarge)
        }

    }

    HourlyWeatherSession(
        weatherList = weatherList
    )
    TenDayWeatherForecast(
        dailyWeatherList = dailyWeatherList
    )

    // 初期表示の天気情報を取得
    LaunchedEffect(Unit) {
        viewModel.fetchWeather("東京")
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = searchText,
        onValueChange = { newText -> searchText = newText },
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        textStyle = TextStyle.Default.copy(fontSize = 16.sp),
        placeholder = {
            Text(text = "都市を入力してください", style = MaterialTheme.typography.bodyLarge)
        },

        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            onSearch(searchText)
            keyboardController?.hide()
        }),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search Icon"
            )
        },

        colors = TextFieldDefaults.outlinedTextFieldColors()
    )
}

@Preview(showBackground = true)
@Composable
fun WeatherAppPreview() {
    WeatherApp(viewModel = WeatherViewModel(WeatherRepository()))
}
