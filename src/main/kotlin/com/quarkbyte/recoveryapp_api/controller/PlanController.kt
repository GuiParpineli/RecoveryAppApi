package com.quarkbyte.recoveryapp_api.controller

import com.quarkbyte.recoveryapp_api.service.PlanService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["plan"], produces = [MediaType.APPLICATION_JSON_VALUE])
class PlanController(private val service: PlanService) {
    @GetMapping
    fun getAll(): ResponseEntity<*> = service.getAll()
}