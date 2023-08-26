package com.teddy.shorturlconverter.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel
) {
    val uiState = homeViewModel.uiState.collectAsState()

    HomeScreen(
        uiState = uiState.value,
        getShortUrl = homeViewModel::getShortUrl
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: HomeViewModel.UiState,
    getShortUrl: (url: String) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        var text by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text(text = "Url") },
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { getShortUrl(text) },
            modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
        ) {
            Text(text = "Convert")
        }

        when (uiState) {
            is HomeViewModel.UiState.Success -> {
                Text(text = uiState.url)
            }

            else -> {}
        }
    }
}