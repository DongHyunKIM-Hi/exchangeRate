package com.example.exchangerate.config

import com.example.exchangerate.api.model.ExchangeData
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig {
    @Value("\${spring.redis.host}")
    lateinit var host: String
    @Value("\${spring.redis.port}")
    lateinit var port: String

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory(host,port.toInt())
    }

    @Bean
    fun redisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate<String, ExchangeData> {
        val redistemplate = RedisTemplate<String,ExchangeData>()
        redistemplate.setConnectionFactory(connectionFactory)
        redistemplate.keySerializer = StringRedisSerializer()
        redistemplate.valueSerializer = Jackson2JsonRedisSerializer(ExchangeData::class.java)
        return redistemplate
    }
}