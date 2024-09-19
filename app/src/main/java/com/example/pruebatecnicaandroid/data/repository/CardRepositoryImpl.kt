package com.example.pruebatecnicaandroid.data.repository

import com.example.pruebatecnicaandroid.data.model.BinResponse
import com.example.pruebatecnicaandroid.data.network.ApiService
import com.example.pruebatecnicaandroid.domain.repository.CardRepository
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(private val apiService: ApiService) : CardRepository {
    override suspend fun validateBin(bin: String): BinResponse? {
        return try {
            val response = apiService.validateCardBin(bin)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}
