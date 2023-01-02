package com.quarkbyte.recoveryapp_api.controller

import com.quarkbyte.recoveryapp_api.model.customer.Customer
import com.quarkbyte.recoveryapp_api.service.customer.CustomerService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping(value = ["customer"], produces = [MediaType.APPLICATION_JSON_VALUE])
class CustomerController(val service: CustomerService) {

    @GetMapping("all")
    fun getAll(): ResponseEntity<*> = service.getAll()

    @GetMapping
    fun getById(@RequestParam("id") id: UUID) = service.getById(id)

    @GetMapping("byemail")
    fun getByEmail(@RequestParam("email") email:String,
                   @RequestParam("cpf") cpf: String) = service.getByEmailorCpf(email, cpf)

    @GetMapping("byname")
    fun getByName(@RequestParam("name") name: String) = service.getByName(name)

    @PostMapping
    fun save(@RequestBody customer: Customer) = service.save(customer)

    @PatchMapping
    fun update(@RequestBody customer: Customer) = service.update(customer)

    @DeleteMapping
    fun delete(@RequestParam("id") id: UUID) = service.delete(id)

}