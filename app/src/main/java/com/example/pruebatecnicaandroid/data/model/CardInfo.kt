package com.example.pruebatecnicaandroid.data.model

data class BinResponse(
    val success: Boolean,
    val code: Int,
    val BIN: BinData?
)

data class BinData(
    val valid: Boolean,
    val number: Int,
    val length: Int,
    val scheme: String,
    val brand: String,
    val type: String,
    val level: String,
    val is_commercial: String,
    val is_prepaid: String,
    val currency: String,
    val issuer: Issuer?,
    val country: Country?
)

data class Issuer(
    val name: String,
    val website: String?,
    val phone: String?
)

data class Country(
    val name: String,
    val native: String,
    val flag: String,
    val numeric: String,
    val capital: String,
    val currency: String,
    val currency_name: String,
    val currency_symbol: String,
    val region: String,
    val subregion: String,
    val idd: String,
    val alpha2: String,
    val alpha3: String,
    val language: String,
    val language_code: String
)


