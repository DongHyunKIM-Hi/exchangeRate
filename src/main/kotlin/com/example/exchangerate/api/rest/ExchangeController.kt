package com.example.exchangerate.api.rest

import com.example.exchangerate.handler.OpenApiHandler
import com.example.exchangerate.handler.response.ExchangeDataResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

@RestController
class ExchangeController(
    private val openApiHandler: OpenApiHandler
) {
    @Value("\${openApi.access_key}")
    lateinit var apiToken:String

    @GetMapping("/test")
    fun test(): ExchangeDataResponse {
        val apiresult =  openApiHandler.getExchangeDate(apiToken).execute()
        return apiresult.body()!!
    }
}