package com.quarkbyte.recoveryapp_api.controller

import com.quarkbyte.recoveryapp_api.service.CaseService
import com.quarkbyte.recoveryapp_api.service.SinistroService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["case"], produces = [MediaType.APPLICATION_JSON_VALUE])
class CaseController(private val service: CaseService, private val sinsitroService: SinistroService) {

    @GetMapping
    fun getALl(): ResponseEntity<*> = service.getAll()

    @GetMapping("sinistro")
    fun getAllSinistros(): ResponseEntity<*> = sinsitroService.get();
}