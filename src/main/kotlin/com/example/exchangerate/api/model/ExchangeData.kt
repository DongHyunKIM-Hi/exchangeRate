package com.example.exchangerate.api.model

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("exchangeRate")
class ExchangeData {
    @Id
    var country: String =""
    var rate : Number = 0
}

fun exchangeDate(country:String, rate:Number): ExchangeData {
    return ExchangeData().apply {
        this.country = country
        this.rate = rate
    }
}