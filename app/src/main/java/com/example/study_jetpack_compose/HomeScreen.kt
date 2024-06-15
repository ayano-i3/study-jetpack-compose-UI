package com.example.study_jetpack_compose

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

//@Composable
//fun HomeScreen(
////    weatherUiState: String,
//    modifier: Modifier = Modifier,
//    contentPadding: PaddingValues = PaddingValues(0.dp)
//) {
//    ResultScreen(
////        weatherUiState,
//        modifier = modifier,
//    )
//}

@Composable
fun ResultScreen(
    photos: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = photos)
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    ResultScreen("Placeholder")
}

//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen(
//        weatherUiState = "Placeholder",
//    )
//}