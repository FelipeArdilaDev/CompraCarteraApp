package com.example.pruebatecnicaandroid.data.local

import android.content.Context

class PreferencesHelper(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun saveBalance(balance: Long) {
        with(sharedPreferences.edit()) {
            putLong("user_balance", balance)
            apply()
        }
    }

    fun getBalance(): Long {
        return sharedPreferences.getLong("user_balance", 0L)
    }
}
