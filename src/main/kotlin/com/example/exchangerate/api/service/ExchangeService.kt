package com.example.exchangerate.api.service

import com.example.exchangerate.api.model.ExchangeData
import com.example.exchangerate.api.model.exchangeDate
import com.example.exchangerate.common.exception.BadRequestException
import com.example.exchangerate.handler.OpenApiHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
interface ExchangeService{
    fun getExchangeData(country:String) : ExchangeData?
}


@Service
class ExchangeServiceImpl(
    private val redisTemplate: RedisTemplate<String, ExchangeData>,
    private val openApiHandler: OpenApiHandler
):ExchangeService {
    @Value("\${openApi.access_key}")
    lateinit var apiToken:String

    override fun getExchangeData(country:String): ExchangeData? {
        val apiResponse = openApiHandler.getExchangeDate(apiToken).execute()
        when(apiResponse.code()){
            200 -> {
                val exchangeDate = exchangeDate(country, apiResponse.body()!!)
                redisTemplate.opsForValue().set(country,exchangeDate, 2,TimeUnit.MINUTES)
            }
            else -> throw BadRequestException("test2")
        }
        return redisTemplate.opsForValue().get(country)
    }
}