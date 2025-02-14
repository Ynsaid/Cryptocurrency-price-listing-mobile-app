package com.example.cryptocurrencyapp
// class of api response
data class Crypto (
    val id: String,
    val name: String,
    val symbol: String,
    val price_usd: String
    )