package com.teddy.shorturlconverter.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teddy.shorturlconverter.data.ConvertRepository
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
            _uiState.update {
                UiState.Success(response.result.url)
            }
        }
    }

    sealed interface UiState {
        object Empty : UiState
        object Error : UiState
        data class Success(
            val url: String
        ) : UiState
    }
}