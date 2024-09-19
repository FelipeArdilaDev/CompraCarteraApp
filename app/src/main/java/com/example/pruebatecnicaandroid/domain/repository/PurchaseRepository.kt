package com.example.pruebatecnicaandroid.domain.repository

interface PurchaseRepository {
    fun processPurchase(transactionNumber: String): PurchaseResult
}