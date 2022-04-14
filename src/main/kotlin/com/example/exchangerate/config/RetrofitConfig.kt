package com.example.exchangerate.config

import com.example.exchangerate.handler.OpenApiHandler
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Configuration
class RetrofitConfig {
    @Value("\${openApi.url}")
    lateinit var apiUrl:String


    @Bean
    fun openApiService(objectMapper: ObjectMapper):OpenApiHandler{
        val retrofit = Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(OpenApiHandler::class.java)
    }
}