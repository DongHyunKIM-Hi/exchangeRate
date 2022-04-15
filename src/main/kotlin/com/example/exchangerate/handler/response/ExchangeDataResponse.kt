package com.example.exchangerate.handler.response

import com.google.gson.annotations.SerializedName

data class ExchangeDataResponse(
    val quotes: ExchangeDataDetailResponse
)

data class ExchangeDataDetailResponse(
    @SerializedName("USDKRW")
    val KRW: Number,
    @SerializedName("USDJPY")
    val JPY: Number,
    @SerializedName("USDPHP")
    val PHP: Number
)