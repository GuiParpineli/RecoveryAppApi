package com.quarkbyte.recoveryapp_api.controller

import com.quarkbyte.recoveryapp_api.model.customer.Bondsman
import com.quarkbyte.recoveryapp_api.service.customer.BondsmanService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping(value = ["bondsman"], produces = [MediaType.APPLICATION_JSON_VALUE])
class BondsmanController(private val service: BondsmanService) {
    @GetMapping("all")
    fun getAll() = service.getAll()

    @GetMapping
    fun getById(@RequestParam("id") id: UUID) = service.getById(id)

    @GetMapping("byemail")
    fun getByEmail(@RequestParam("email") email:String,
                   @RequestParam("cpf") cpf: String) = service.getByEmailorCpf(email, cpf)

    @GetMapping("byname")
    fun getByName(@RequestParam("name") name: String) = service.getByName(name)

    @PostMapping("register")
    fun save(@RequestBody bondsman: Bondsman) = service.save(bondsman)

    @PatchMapping
    fun update(@RequestBody bondsman: Bondsman) = service.update(bondsman)

    @DeleteMapping
    fun update(@RequestParam("id") id: UUID) = service.delete(id)

}