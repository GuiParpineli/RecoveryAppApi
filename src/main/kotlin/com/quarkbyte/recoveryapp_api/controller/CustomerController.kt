package com.quarkbyte.recoveryapp_api.controller

import com.quarkbyte.recoveryapp_api.service.CustomerService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["customer"], produces = [MediaType.APPLICATION_JSON_VALUE])
class CustomerController(val service: CustomerService) {

    @GetMapping()
    fun findAll(): ResponseEntity<Any> = service.getAll()
}