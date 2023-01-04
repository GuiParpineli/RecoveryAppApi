package com.quarkbyte.recoveryapp_api.controller

import com.quarkbyte.recoveryapp_api.model.plan.Plan
import com.quarkbyte.recoveryapp_api.model.dto.PlanDTO
import com.quarkbyte.recoveryapp_api.model.dto.PlanInput
import com.quarkbyte.recoveryapp_api.service.plan.PlanService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(value = ["plan"], produces = [MediaType.APPLICATION_JSON_VALUE])
class PlanController(private val service: PlanService) {
    @GetMapping("all")
    fun getAll(): ResponseEntity<*> = service.getAll()

    @GetMapping("allfull")
    fun getAllFull(): ResponseEntity<*> = service.getAllFull()

    @GetMapping
    fun getById(@RequestParam("id") id: UUID) = service.getById(id)

    @GetMapping("allbyid")
    fun getAllbyId(@RequestParam(value = "id") ids: List<UUID>) = service.getAllById(ids)

    @PostMapping("save")
    fun save(@RequestBody plan: PlanDTO) = service.save(plan)

    @PatchMapping
    fun update(@RequestBody plan: PlanInput) = service.update(plan)

    @DeleteMapping
    fun delete(@RequestParam("id") id: UUID) = service.delete(id)
}