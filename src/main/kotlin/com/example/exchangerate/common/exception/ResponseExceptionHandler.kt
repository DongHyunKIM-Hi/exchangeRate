package com.example.exchangerate.common.exception

import com.example.exchangerate.common.exception.response.ErrorResponse
import com.example.exchangerate.common.exception.response.ErrorResponse.Companion.of
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception
import java.lang.RuntimeException

@RestControllerAdvice
class ResponseExceptionHandler: ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [
        BadRequestException::class,
        NotFoundException::class
    ])
    protected fun handlerError(runtimeException: RuntimeException, request: WebRequest): ResponseEntity<Any>{
        var statusCode = HttpStatus.BAD_REQUEST
        when(runtimeException){
            is BadRequestException -> statusCode = HttpStatus.BAD_REQUEST
            is NotFoundException -> statusCode = HttpStatus.NOT_FOUND
        }

        var path: String? = null
        try {
            val servletRequestAttributes = request as ServletRequestAttributes
            path = servletRequestAttributes.request.requestURI
        }catch (ex: Exception){
            ex.printStackTrace()
        }
        val errorResponse: ErrorResponse = of(statusCode, runtimeException.message, path)
        return handleExceptionInternal(runtimeException,errorResponse, HttpHeaders(), statusCode, request)
    }
}