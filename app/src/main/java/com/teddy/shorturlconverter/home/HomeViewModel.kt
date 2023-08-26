package com.teddy.shorturlconverter.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teddy.shorturlconverter.data.ConvertRepository
import com.teddy.shorturlconverter.model.Error
import com.teddy.shorturlconverter.model.UrlResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val convertRepository = ConvertRepository()

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Empty)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun getShortUrl(url: String) {
        viewModelScope.launch {
            val response = convertRepository.getShortUrl(url)

            when (response) {
                is UrlResponse -> {
                    _uiState.update {
                        UiState.Success(response.result.url)
                    }
                }

                is Error -> {
                    _uiState.update {
                        UiState.Error(code = response.status, message = response.message)
                    }
                }
            }
        }
    }

    sealed interface UiState {
        object Empty : UiState
        data class Error(
            val code: Int,
            val message: String
        ) : UiState

        data class Success(
            val url: String
        ) : UiState
    }
}