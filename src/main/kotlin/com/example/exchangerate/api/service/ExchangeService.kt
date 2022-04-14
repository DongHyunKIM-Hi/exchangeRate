package com.example.exchangerate.api.service

import com.example.exchangerate.api.model.ExchangeData
import com.example.exchangerate.handler.OpenApiHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
interface ExchangeService{
    fun getExchangeData()
}


@Service
class ExchangeServiceImpl(
    private val redisTemplate: RedisTemplate<String, ExchangeData>,
    private val openApiHandler: OpenApiHandler
):ExchangeService {
    @Value("\${openApi.access_key}")
    lateinit var apiToken:String

    override fun getExchangeData() {
        val apiResponse = openApiHandler.getExchangeDate(apiToken).execute()
        when(apiResponse.code()){
            200 -> {}
        }
    }
}