package com.teddy.shorturlconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.teddy.shorturlconverter.home.HomeRoute
import com.teddy.shorturlconverter.home.HomeViewModel
import com.teddy.shorturlconverter.ui.theme.ShortUrlConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShortUrlConverterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel : HomeViewModel by viewModels()

                    HomeRoute(viewModel)
                }
            }
        }
    }
}
