package com.example.exchangerate.api.rest

import com.example.exchangerate.api.model.ExchangeData
import com.example.exchangerate.api.model.exchangeDate
import com.example.exchangerate.api.service.ExchangeService
import com.example.exchangerate.handler.OpenApiHandler
import com.example.exchangerate.handler.response.ExchangeDataResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

@RestController
@RequestMapping("/api/countries")
class ExchangeController(
    private val exchangeService: ExchangeService
) {

    @GetMapping("/exchange-rate")
    fun getExchangeRate(@RequestParam country : String): ExchangeData? {
        val result = exchangeService.getExchangeData(country)
        return result
    }
}