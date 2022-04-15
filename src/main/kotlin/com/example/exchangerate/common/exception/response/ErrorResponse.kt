package com.example.exchangerate.common.exception.response

import org.springframework.http.HttpStatus
import java.util.*

class ErrorResponse {
    var timestamp: Long = 0
    var status = 0
    var error: String? = null
    var message: String? = null
    var path: String? = null
    override fun toString(): String {
        return "ErrorResponse{" +
                "timestamp=" + timestamp +
                ", status=" + status +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                '}'
    }

    companion object {
        fun of(status: HttpStatus, message: String?, path: String?): ErrorResponse{
            val errorResponse = ErrorResponse()
            errorResponse.timestamp = Date().getTime()
            errorResponse.status = status.value()
            errorResponse.error = status.reasonPhrase
            errorResponse.message = message
            errorResponse.path = path
            return errorResponse
        }
    }
}