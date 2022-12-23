package com.quarkbyte.recoveryapp_api.model.mapper

import com.quarkbyte.recoveryapp_api.controller.BondsmanController
import com.quarkbyte.recoveryapp_api.controller.CaseController
import com.quarkbyte.recoveryapp_api.controller.CustomerController
import com.quarkbyte.recoveryapp_api.controller.ProductController
import com.quarkbyte.recoveryapp_api.exceptions.ResourceNotFoundException
import com.quarkbyte.recoveryapp_api.model.dto.PlanDTO
import com.quarkbyte.recoveryapp_api.model.dto.PlanOutput
import com.quarkbyte.recoveryapp_api.model.plan.Plan
import com.quarkbyte.recoveryapp_api.repository.*
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilderFactory
import org.springframework.stereotype.Component
import java.util.Arrays
import java.util.UUID

@Component
class PlanMapper(
    val userRepository: UserRepository,
    val productRepository: ProductRepository,
    val customerRepository: CustomerRepository,
    val bondsmanRepository: BondsmanRepository,
    val caseRepository: CaseRepository
) {

    fun map(input: PlanDTO): Plan {
        val product =
            productRepository.findById(input.productListId[0])
                .orElseThrow { ResourceNotFoundException("none plans founded") }
        val analyst =
            userRepository.findById(input.analystId).orElseThrow { ResourceNotFoundException("none plans founded") }
        val customer =
            customerRepository.findById(input.customerId)
                .orElseThrow { ResourceNotFoundException("none plans founded") }
        val bondsman =
            bondsmanRepository.findById(input.bondsmanId)
                .orElseThrow { ResourceNotFoundException("none plans founded") }
        val case =
            caseRepository.findById(input.caseCSJId).orElseThrow { ResourceNotFoundException("none plans founded") }

        return Plan(
            value = input.value,
            planStatus = input.planStatus,
            productList = listOf(product),
            customer = customer,
            bondsman = bondsman,
            caseCSJ = case
        )
    }

    fun map(plan: Plan): PlanOutput {
        return PlanOutput(
            id = plan.id!!,
            value = plan.value!!,
            planStatus = plan.planStatus!!,
            creationDate = plan.creationDate
        )
    }

    fun buildPlanOutput(plan: Plan, output: PlanOutput): EntityModel<PlanOutput> {

        var productLink: Link? = null
        var productsIdlist = mutableListOf<String>()

        plan.productList?.forEach { i ->
            productsIdlist.add(i.id.toString())
        }
        productLink = WebMvcLinkBuilder.linkTo(ProductController::class.java)
            .slash("/allbyid?id=" + productsIdlist.joinToString(separator = ","))
            .withRel("product 1")
            .withTitle(plan.productList?.get(0)?.name.toString())


        val customerLink = WebMvcLinkBuilder.linkTo(CustomerController::class.java)
            .slash("?id=" + plan.customer!!.id)
            .withRel("customer")

        val bondsmanLInk = WebMvcLinkBuilder.linkTo(BondsmanController::class.java)
            .slash("?id=" + plan.bondsman!!.id)
            .withRel("bondsman")

        val caseCSJLink = WebMvcLinkBuilder.linkTo(CaseController::class.java)
            .slash("?id=" + plan.caseCSJ!!.id)
            .withRel("caseCSJ")

        return EntityModel.of(output, productLink, customerLink, bondsmanLInk, caseCSJLink)
    }

}

