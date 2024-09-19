package com.example.pruebatecnicaandroid.domain.repository

import com.example.pruebatecnicaandroid.data.model.BinResponse


interface CardRepository {
    suspend fun validateBin(bin: String): BinResponse?
}