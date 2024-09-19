package com.example.pruebatecnicaandroid.presentacion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pruebatecnicaandroid.domain.usecase.GetUserInfoUseCase

class DashboardViewModelFactory(
    private val userInfoUseCase: GetUserInfoUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DashboardViewModel(userInfoUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

