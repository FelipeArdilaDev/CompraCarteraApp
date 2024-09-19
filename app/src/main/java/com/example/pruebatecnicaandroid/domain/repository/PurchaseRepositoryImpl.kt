package com.example.pruebatecnicaandroid.domain.repository

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


data class PurchaseResult(val success: Boolean, val timestamp: String?)

class PurchaseRepositoryImpl : PurchaseRepository {
    override fun processPurchase(transactionNumber: String): PurchaseResult {
        val success = transactionNumber == "1234567890"
        val timestamp = if (success) {
            SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.getDefault()).format(Date())
        } else {
            null
        }
        return PurchaseResult(success, timestamp)
    }
}