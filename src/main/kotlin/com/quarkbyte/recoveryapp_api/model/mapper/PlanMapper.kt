package com.quarkbyte.recoveryapp_api.model.mapper

import com.quarkbyte.recoveryapp_api.controller.*
import com.quarkbyte.recoveryapp_api.exceptions.ResourceNotFoundException
import com.quarkbyte.recoveryapp_api.model.dto.PlanDTO
import com.quarkbyte.recoveryapp_api.model.dto.PlanOutput
import com.quarkbyte.recoveryapp_api.model.plan.Plan
import com.quarkbyte.recoveryapp_api.model.plan.Product
import com.quarkbyte.recoveryapp_api.repository.*
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.stereotype.Component

@Component
class PlanMapper(
    val userRepository: UserRepository,
    val productRepository: ProductRepository,
    val customerRepository: CustomerRepository,
    val bondsmanRepository: BondsmanRepository,
    val caseRepository: CaseRepository
) {

    fun map(input: PlanDTO): Plan {
        val product = mutableListOf<Product>()

        for (i in input.productListId.indices) {
            product.add(
                productRepository.findById(input.productListId[i])
                    .orElseThrow { ResourceNotFoundException("none plans founded") }
            )
        }
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
            productList = product,
            customer = customer,
            analyst = analyst,
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
        val productsIdList = mutableListOf<String>()
        var value = 0.0
        plan.productList?.map { value += it.value!! }
        plan.productList?.forEach { i -> productsIdList.add(i.id.toString()) }

        productLink = WebMvcLinkBuilder.linkTo(ProductController::class.java)
            .slash("/allbyid?id=" + productsIdList.joinToString(separator = ","))
            .withRel("products")
            .withTitle("und: ${productsIdList.size}, totalValue: $value ")

        val customerLink = WebMvcLinkBuilder.linkTo(CustomerController::class.java)
            .slash("?id=" + plan.customer!!.id)
            .withRel("customer")
            .withTitle(plan.customer.name.toString())

        val analystLink = WebMvcLinkBuilder.linkTo(UserController::class.java)
            .slash("?id=" + plan.analyst!!.id)
            .withRel("Analyst")
            .withTitle(plan.analyst.name.toString())

        val bondsmanLInk = WebMvcLinkBuilder.linkTo(BondsmanController::class.java)
            .slash("?id=" + plan.bondsman!!.id)
            .withRel("bondsman")
            .withTitle(plan.bondsman.name.toString())

        val caseCSJLink = WebMvcLinkBuilder.linkTo(CaseController::class.java)
            .slash("?id=" + plan.caseCSJ!!.id)
            .withRel("caseCSJ")
            .withTitle(plan.caseCSJ.typeCaseCSJ.toString())

        return EntityModel.of(output, productLink, customerLink, bondsmanLInk, caseCSJLink, analystLink)
    }

}

