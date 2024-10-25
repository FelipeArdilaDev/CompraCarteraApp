package com.example.pruebatecnicaandroid.presentacion.views.ui.model

sealed interface CardUiState {
    object  Loading : CardUiState
    data class Error(val throwable: Throwable) : CardUiState
    data class Success(val tasks: List<CardUiState>) : CardUiState
}