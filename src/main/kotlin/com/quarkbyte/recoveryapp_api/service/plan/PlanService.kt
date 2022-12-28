package com.quarkbyte.recoveryapp_api.service.plan

import com.quarkbyte.recoveryapp_api.exceptions.FinalDataException
import com.quarkbyte.recoveryapp_api.exceptions.ResourceNotFoundException
import com.quarkbyte.recoveryapp_api.exceptions.SaveErrorException
import com.quarkbyte.recoveryapp_api.model.plan.Plan
import com.quarkbyte.recoveryapp_api.model.dto.PlanDTO
import com.quarkbyte.recoveryapp_api.model.dto.PlanInput
import com.quarkbyte.recoveryapp_api.model.dto.PlanOutput
import com.quarkbyte.recoveryapp_api.model.mapper.PlanMapper
import com.quarkbyte.recoveryapp_api.repository.CaseRepository
import com.quarkbyte.recoveryapp_api.repository.PlanRepository
import org.springframework.hateoas.EntityModel
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class PlanService(
    private val repository: PlanRepository,
    private val caseRepository: CaseRepository,
    val mapper: PlanMapper
) {

    fun getAll(): ResponseEntity<*> {
        val plans = repository.findAll()
        val output: MutableList<PlanOutput> = mutableListOf()
        plans.forEach { p -> output.add( mapper.map(p)) }
        val saved: MutableList<EntityModel<PlanOutput>> = mutableListOf()
        for (p in plans.indices) {
            saved.add(mapper.buildPlanOutput(plans[p], output[p]))
        }
        return ResponseEntity.ok(saved)
    }

    @Throws(ResourceNotFoundException::class)
    fun getAllFull(): ResponseEntity<*> {
        val saved = repository.findAll()
        if (saved.isEmpty()) throw ResourceNotFoundException("None plans founded")
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
    fun update(input: PlanInput): ResponseEntity<*> {

        val saved = repository.findById(input.id)
        var plan: Plan? = null

        val copy = Plan(
            input.id,
            input.planStatus,
            input.value,
            saved.get().analyst,
            saved.get().initialDate,
            input.finalDate,
            input.productList ?: saved.get().productList,
            saved.get().customer,
            saved.get().bondsman,
            saved.get().caseCSJ
        )

        if (copy.finalDate != null)
            if (copy.finalDate < copy.initialDate)
                throw FinalDataException("Final date cannot be less than start date ")

        if (saved.isPresent)
            plan = repository.saveAndFlush(copy)

        return ResponseEntity.ok(plan)
    }

    @Throws(ResourceNotFoundException::class)
    fun delete(id: UUID): ResponseEntity<*> {
        val saved = repository.findById(id)
        if (saved.isPresent) {
            repository.deleteById(id)
            caseRepository.deleteById(saved.get().caseCSJ?.id!!)
        }
        return ResponseEntity.ok("deleted successfully")
    }
}