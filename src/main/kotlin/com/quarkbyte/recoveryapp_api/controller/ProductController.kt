package com.quarkbyte.recoveryapp_api.controller

import com.quarkbyte.recoveryapp_api.model.Plan
import com.quarkbyte.recoveryapp_api.model.Product
import com.quarkbyte.recoveryapp_api.service.ProductService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(value = ["product"], produces = [MediaType.APPLICATION_JSON_VALUE])
class ProductController(private val service: ProductService) {

    fun getALl(): ResponseEntity<*> = service.getAll()
    @GetMapping("id")
    fun getById(@RequestParam("id") id: UUID) = service.getById(id)
    @PostMapping
    fun save(@RequestBody product: Product) = service.save(product)
    @PatchMapping
    fun update(@RequestBody product: Product) = service.update(product)
    @DeleteMapping
    fun delete(@RequestParam("id") id: UUID) = service.delete(id)
}