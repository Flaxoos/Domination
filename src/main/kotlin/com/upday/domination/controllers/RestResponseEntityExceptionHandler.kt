package com.upday.domination.controllers

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@ControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [NoSuchElementException::class])
    fun handleNotFound(e: RuntimeException, request: WebRequest): ResponseEntity<Any> {
        val bodyOfResponse = "Resource not found"
        return handleExceptionInternal(e, bodyOfResponse, HttpHeaders(), HttpStatus.NOT_FOUND, request)
    }

}
