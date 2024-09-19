package com.example.pruebatecnicaandroid.data.network

import com.example.pruebatecnicaandroid.data.model.BinResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("/")
    suspend fun validateCardBin(@Query("bin") bin: String): Response<BinResponse>
}


