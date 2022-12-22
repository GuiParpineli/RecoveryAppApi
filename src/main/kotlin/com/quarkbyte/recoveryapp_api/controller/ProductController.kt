package com.quarkbyte.recoveryapp_api.controller

import com.quarkbyte.recoveryapp_api.service.ProductService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["product"], produces = [MediaType.APPLICATION_JSON_VALUE])
class ProductController(private val service: ProductService) {

    fun getALl(): ResponseEntity<*> = service.getAll()
}