package com.example.pruebatecnicaandroid.domain.usecase

import com.example.pruebatecnicaandroid.data.model.BinResponse
import com.example.pruebatecnicaandroid.domain.repository.CardRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCardUseCase @Inject constructor(
    private val cardRepository: CardRepository
) {
    suspend operator fun invoke(bin: String): BinResponse? {
        return cardRepository.validateBin(bin)
    }
}