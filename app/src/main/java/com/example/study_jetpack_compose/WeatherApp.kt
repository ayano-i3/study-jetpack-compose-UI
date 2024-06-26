@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.study_jetpack_compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        // スクロールの挙動を設定
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
//        topBar = {
//            WeatherTopAppBar(scrollBehavior = scrollBehavior)
//        },
        content = { paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues)
            ) {
                Column {
                    SearchBar(
                        modifier = Modifier.padding(8.dp),
                        onSearch = { query -> viewModel.fetchWeather(query)}
                    )
                    WeatherScreen(viewModel)
                }
            }
        }
    )

}
@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    val weather by viewModel.weatherData.observeAsState()

    weather?.let { weatherResponse ->
        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//                .verticalScroll(rememberScrollState())
        ) {
//            LocationBar(location = weatherResponse.name)
            WeatherInfoScreen(weatherResponse = weatherResponse)

            // 1時間ごとの天気予報
//            HourlyForecastSection(hourlyWeather = weatherResponse.weather)

//            DailyForecastSection()
        }
    } ?: run {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Loading...", style = MaterialTheme.typography.bodyLarge)
        }
    }


    LaunchedEffect(Unit) {
        viewModel.fetchWeather("Tokyo")
    }
}

@Composable
fun WeatherTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = "Weather App",
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier
    )

}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit
) {
    var searchText by remember { mutableStateOf("")}
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = searchText,
        onValueChange = { newText -> searchText = newText},
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

//@Composable
//fun HourlyForecastSection(hourlyWeather: List<Weather>) {
//    Column(
//        modifier = Modifier.padding(vertical = 16.dp)
//    ) {
//        Text(text = "1時間ごとの天気予報", style = MaterialTheme.typography.titleSmall)
//        LazyRow {
//            items(hourlyWeather) { weather ->
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier.padding(8.dp)
//                ) {
//                    Text(text = "${weather.temp}", style = MaterialTheme.typography.bodyMedium)
//                    Text(text = "${weather.pop}", style = MaterialTheme.typography.bodyMedium)
//                    val iconUrl = "https://openweathermap.org/img/wn/${weather.icon}@2x.png"
//                    Icon(
//                        painter = rememberAsyncImagePainter(iconUrl),
//                        contentDescription = "Cloudy",
//                        modifier = Modifier.size(32.dp)
//                    )
//                    Text(text = "${weather}:00", style = MaterialTheme.typography.bodyMedium)
//                }
//            }
//        }
//    }
//}

@Preview(showBackground = true)
@Composable
fun WeatherAppPreview() {
    WeatherApp(viewModel = WeatherViewModel(WeatherRepository()))
}
