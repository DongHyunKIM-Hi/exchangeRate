package com.example.exchangerate.handler

import com.example.exchangerate.handler.response.ExchangeDataResponse
import org.springframework.stereotype.Service
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

@Service
interface OpenApiHandler {
    @GET("/live")
    fun getExchangeDate(@Query("access_key") accessKey:String): Call<ExchangeDataResponse>
}