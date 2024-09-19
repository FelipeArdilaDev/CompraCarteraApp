package com.example.pruebatecnicaandroid.domain.usecase

import com.example.pruebatecnicaandroid.domain.repository.PurchaseRepository
import com.example.pruebatecnicaandroid.domain.repository.PurchaseResult
import javax.inject.Inject

class ProcessPurchaseUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    fun execute(transactionNumber: String): PurchaseResult {
        return purchaseRepository.processPurchase(transactionNumber)
    }
}