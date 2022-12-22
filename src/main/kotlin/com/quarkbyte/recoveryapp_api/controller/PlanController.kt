package com.quarkbyte.recoveryapp_api.controller

import com.quarkbyte.recoveryapp_api.model.Customer
import com.quarkbyte.recoveryapp_api.model.Plan
import com.quarkbyte.recoveryapp_api.service.PlanService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(value = ["plan"], produces = [MediaType.APPLICATION_JSON_VALUE])
class PlanController(private val service: PlanService) {
    @GetMapping
    fun getAll(): ResponseEntity<*> = service.getAll()
    @GetMapping("id")
    fun getById(@RequestParam("id") id: UUID) = service.getById(id)
    @PostMapping
    fun save(@RequestBody plan: Plan) = service.save(plan)
    @PatchMapping
    fun update(@RequestBody plan: Plan) = service.update(plan)
    @DeleteMapping
    fun delete(@RequestParam("id") id: UUID) = service.delete(id)
}