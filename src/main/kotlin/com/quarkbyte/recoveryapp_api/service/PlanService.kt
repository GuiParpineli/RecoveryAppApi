package com.quarkbyte.recoveryapp_api.service

import com.quarkbyte.recoveryapp_api.exceptions.ResourceNotFoundException
import com.quarkbyte.recoveryapp_api.exceptions.SaveErrorException
import com.quarkbyte.recoveryapp_api.model.Plan
import com.quarkbyte.recoveryapp_api.model.dto.PlanDTO
import com.quarkbyte.recoveryapp_api.model.dto.PlanOutput
import com.quarkbyte.recoveryapp_api.model.mapper.PlanMapper
import com.quarkbyte.recoveryapp_api.repository.PlanRepository
import org.springframework.hateoas.EntityModel
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class PlanService(
    private val repository: PlanRepository,
    val mapper: PlanMapper
) {

    fun getAll(): ResponseEntity<*> {
        val plans = repository.findAll()
        val output: MutableList<PlanOutput> = mutableListOf()
        plans.forEach { p -> output.add(mapper.map(p)) }
        val saved: MutableList<EntityModel<PlanOutput>> = mutableListOf()
        for (p in plans) {
            for (o in output) {
                saved.add(
                    mapper.buildPlanOutput(p, o)
                )
            }
        }
        return ResponseEntity.ok(saved)
    }

    @Throws(ResourceNotFoundException::class)
    fun getById(id: UUID): ResponseEntity<*> {
        val saved = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("None  plans founded") }!!
        return ResponseEntity.ok(saved)
    }

    @Throws(SaveErrorException::class)
    fun save(input: PlanDTO): ResponseEntity<*> {
        val plan: Plan = repository.save(mapper.map(input))
        val output = mapper.map(plan)
        val saved = mapper.buildPlanOutput(plan, output)
        return ResponseEntity.ok(saved)
    }

    @Throws(SaveErrorException::class)
    fun update(customer: Plan): ResponseEntity<*> {
        val saved: Plan = try {
            repository.saveAndFlush(customer)
        } catch (e: Exception) {
            throw SaveErrorException("Error, not saved")
        }
        return ResponseEntity.ok(saved)
    }

    @Throws(ResourceNotFoundException::class)
    fun delete(id: UUID): ResponseEntity<*> {
        val saved = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("None plans founded") }!!
        repository.deleteById(id)
        return ResponseEntity.ok("$saved deleted sucessfully")
    }
}