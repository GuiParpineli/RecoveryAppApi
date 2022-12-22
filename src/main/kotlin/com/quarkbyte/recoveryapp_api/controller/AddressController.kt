package com.quarkbyte.recoveryapp_api.controller

import com.quarkbyte.recoveryapp_api.model.Address
import com.quarkbyte.recoveryapp_api.service.AddressService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping(value = ["address"], produces = [MediaType.APPLICATION_JSON_VALUE])
class AddressController(private val service: AddressService) {
    @GetMapping
    fun getAll(): ResponseEntity<*> = service.getAll()
    @GetMapping("id")
    fun getById(@RequestParam("id") id: UUID) = service.getById(id)
    @PostMapping
    fun save(@RequestBody address: Address) = service.save(address)
    @PatchMapping
    fun update(@RequestBody address: Address) = service.update(address)
    @DeleteMapping
    fun delete(@RequestParam("id") id: UUID) = service.delete(id)

}