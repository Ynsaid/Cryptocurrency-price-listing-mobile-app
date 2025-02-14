package com.example.cryptocurrencyapp


import retrofit2.Call
import retrofit2.http.GET
// interface of api service
interface ApiService {
    @GET("tickers/?start=0&limit=100")
    fun getCryptoData(): Call<ApiResponse>
}