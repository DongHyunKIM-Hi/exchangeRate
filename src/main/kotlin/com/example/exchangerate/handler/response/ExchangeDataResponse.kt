package com.example.exchangerate.handler.response

data class ExchangeDataResponse(
    val quotes: ExchangeDataDetailResponse
)

data class ExchangeDataDetailResponse(
    val USDKRW: Number,
    val USDJPY: Number,
    val USDPHP: Number
)