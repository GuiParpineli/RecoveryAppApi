package com.quarkbyte.recoveryapp_api.service.plan

import com.quarkbyte.recoveryapp_api.exceptions.FinalDataException
import com.quarkbyte.recoveryapp_api.exceptions.ResourceNotFoundException
import com.quarkbyte.recoveryapp_api.exceptions.SaveErrorException
import com.quarkbyte.recoveryapp_api.model.cases.CaseCSJ
import com.quarkbyte.recoveryapp_api.model.plan.Plan
import com.quarkbyte.recoveryapp_api.model.dto.PlanDTO
import com.quarkbyte.recoveryapp_api.model.dto.PlanInput
import com.quarkbyte.recoveryapp_api.model.dto.PlanOutput
import com.quarkbyte.recoveryapp_api.model.enums.csj.InternalStatus
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
        plans.forEach { p -> output.add(mapper.map(p)) }
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
    fun getAllById(ids: List<UUID>): ResponseEntity<*> {
        val saved = repository.findAllById(ids)
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

        val founded = repository.findAll()
        val haveplan = founded.filter { it.customer.id == input.customerId }
        val plan: Plan?
        var saved: EntityModel<PlanOutput>? = null
        val listofCases = mutableListOf<UUID>()

        //se ha houver um plano salvo fazer isso
        if (haveplan.isNotEmpty() && haveplan.size == 1 && haveplan[0].planStatus) {
            val savedplan = haveplan[0]
            haveplan[0].caseCSJ.forEach { c -> listofCases.add(c?.id!!) }
            input.caseCSJId.forEach { c -> listofCases.add(c) }
            val casesIds = caseRepository.findAllById(listofCases)
            if (haveplan[0].planStatus) {
                val newSave = Plan(
                    planStatus = true,
                    code = savedplan.code,
                    value = savedplan.value,
                    analyst = savedplan.analyst,
                    initialDate = savedplan.initialDate,
                    finalDate = savedplan.finalDate,
                    productList = savedplan.productList,
                    customer = savedplan.customer,
                    bondsman = savedplan.bondsman,
                    caseCSJ = casesIds
                )
                newSave.recidivistCustomer = true
                plan = repository.save(newSave)
                val output = mapper.map(plan)
                saved = mapper.buildPlanOutput(plan, output)
            }
            //mais de um plano salvo isso
        } else if (haveplan.size > 1) {
            val planActive = haveplan.filter { it.planStatus }
            val casesIds = caseRepository.findAllById(listofCases)
            val newSave = Plan(
                planStatus = true,
                code = planActive[0].code,
                value = planActive[0].value,
                analyst = planActive[0].analyst,
                initialDate = planActive[0].initialDate,
                finalDate = planActive[0].finalDate,
                productList = planActive[0].productList,
                customer = planActive[0].customer,
                bondsman = planActive[0].bondsman,
                caseCSJ = casesIds
            )
            newSave.recidivistCustomer = true
            plan = repository.save(newSave)
            val output = mapper.map(plan)
            saved = mapper.buildPlanOutput(plan, output)

        } else {
            plan = repository.save(mapper.map(input))
            val output = mapper.map(plan)
            saved = mapper.buildPlanOutput(plan, output)
        }
        return ResponseEntity.ok(saved)
    }

    @Throws(SaveErrorException::class)
    fun update(input: PlanInput): ResponseEntity<*> {

        val saved = repository.findById(input.id)
        var plan: Plan? = null

        val copy = Plan(
            id = input.id,
            planStatus = input.planStatus,
            code = input.code,
            value = input.value,
            analyst = saved.get().analyst,
            initialDate = saved.get().initialDate,
            finalDate = input.finalDate,
            productList = input.productList ?: saved.get().productList,
            customer = saved.get().customer,
            bondsman = saved.get().bondsman,
            caseCSJ = saved.get().caseCSJ
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
            /*
                        caseRepository.deleteById(saved.get().caseCSJ?.id!!)
            */
        }
        return ResponseEntity.ok("deleted successfully")
    }
}