package com.example.exchangerate.api.model

import com.example.exchangerate.common.exception.BadRequestException
import com.example.exchangerate.handler.response.ExchangeDataResponse
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("exchangeRate")
class ExchangeData {
    @Id
    var country: String =""
    var rate : Number = 0

    //한국 일본 필리핀
}

fun exchangeDate(country:String, exchangeDataResponse: ExchangeDataResponse): ExchangeData {
    return ExchangeData().apply {
        this.country = country
        when(country){
            "KRW" -> this.rate = exchangeDataResponse.quotes.KRW
            "JPY" -> this.rate = exchangeDataResponse.quotes.JPY
            "PHP" -> this.rate = exchangeDataResponse.quotes.PHP
            else -> throw BadRequestException("test")
        }

    }
}