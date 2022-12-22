package com.quarkbyte.recoveryapp_api.controller

import com.quarkbyte.recoveryapp_api.model.Address
import com.quarkbyte.recoveryapp_api.model.Bondsman
import com.quarkbyte.recoveryapp_api.service.BondsmanService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping(value = ["bondsman"], produces = [MediaType.APPLICATION_JSON_VALUE])
class BondsmanController(private val service: BondsmanService) {
    @GetMapping
    fun getAll(): ResponseEntity<*> = service.getAll()

    @GetMapping("id")
    fun getById(id: UUID) = service.getById(id)

    @PostMapping("register")
    fun save(@RequestBody bondsman: Bondsman) = service.save(bondsman)

    @PatchMapping("update")
    fun update(@RequestBody bondsman: Bondsman) = service.update(bondsman)


}