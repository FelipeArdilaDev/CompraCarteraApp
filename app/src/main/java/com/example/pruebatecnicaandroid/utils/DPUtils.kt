package com.example.pruebatecnicaandroid.utils

import java.text.NumberFormat
import java.util.Locale

object DPUtils {

    fun formatCurrency(value: Double): String {
        val formatter = NumberFormat.getNumberInstance(Locale("es", "CO"))
        formatter.minimumFractionDigits = 0
        formatter.maximumFractionDigits = 0
        return formatter.format(value)
    }
}