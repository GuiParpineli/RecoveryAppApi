package com.quarkbyte.recoveryapp_api.controller

import com.quarkbyte.recoveryapp_api.service.AddressService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["address"], produces = [MediaType.APPLICATION_JSON_VALUE])
class AddressController(private val service: AddressService) {
    @GetMapping
    fun getAll(): ResponseEntity<*> = service.getAll()

}