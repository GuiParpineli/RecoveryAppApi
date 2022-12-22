package com.quarkbyte.recoveryapp_api.controller

import com.quarkbyte.recoveryapp_api.service.BondsmanService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["bondsman"], produces = [MediaType.APPLICATION_JSON_VALUE])
class BondsmanController(private val service: BondsmanService) {

   @GetMapping
   fun getAll(): ResponseEntity<*> = service.getAll()
}