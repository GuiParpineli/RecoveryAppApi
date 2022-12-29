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
                    .orElseThrow { ResourceNotFoundException("none products founded") }
            )
        }

        val analyst =
            userRepository.findById(input.analystId)
                .orElseThrow { ResourceNotFoundException("none user founded") }
        val customer =
            customerRepository.findById(input.customerId)
                .orElseThrow { ResourceNotFoundException("none customer founded") }
        val bondsman =
            bondsmanRepository.findById(input.bondsmanId)
                .orElseThrow { ResourceNotFoundException("none bondsman founded") }
        val case =
            caseRepository.findById(input.caseCSJId)
                .orElseThrow { ResourceNotFoundException("none case founded") }

        return Plan(
            planStatus = input.planStatus,
            code = input.code,
            initialDate = input.initialDate,
            finalDate = input.finalDate,
            value = input.value,
            productList = product,
            customer = customer,
            analyst = analyst,
            bondsman = bondsman,
            caseCSJ = case
        )
    }

    fun map(plan: Plan): PlanOutput {
        return PlanOutput(
            code = plan.code,
            value = plan.value,
            recidivistCustomer = plan.recidivistCustomer,
            initialDate = plan.initialDate,
            finalDate = plan.finalDate,
            planStatus = plan.planStatus,
            creationDate = plan.creationDate
        )
    }

    fun buildPlanOutput(plan: Plan, output: PlanOutput): EntityModel<PlanOutput> {

        val productLink: Link?
        val productsIdList = mutableListOf<String>()
        val names = mutableListOf<String>()
        plan.productList.forEach { p -> names.add(p.name.toString()) && productsIdList.add(p.id.toString()) }

        productLink = WebMvcLinkBuilder.linkTo(ProductController::class.java)
            .slash("/allbyid?id=${productsIdList.joinToString(separator = ",")}")
            .withRel("products")
            .withTitle("products: $names")

        val customerLink = WebMvcLinkBuilder.linkTo(CustomerController::class.java)
            .slash("?id=${plan.customer.id}")
            .withRel("customer")
            .withTitle("${plan.customer.name.toString()} ${plan.customer.lastName.toString()}")

        val analystLink = WebMvcLinkBuilder.linkTo(UserController::class.java)
            .slash("?id=${plan.analyst.id}")
            .withRel("Analyst")
            .withTitle(plan.analyst.name!!)

        val bondsmanLInk = WebMvcLinkBuilder.linkTo(BondsmanController::class.java)
            .slash("?id=${plan.bondsman.id}")
            .withRel("bondsman")
            .withTitle(plan.bondsman.name.toString())

        val caseCSJLink = WebMvcLinkBuilder.linkTo(CaseController::class.java)
            .slash("${plan.caseCSJ?.typeCaseCSJ?.lowercase()}?id=${plan.caseCSJ!!.id}")
            .withRel("caseCSJ")
            .withTitle(plan.caseCSJ.typeCaseCSJ)

        return EntityModel.of(output, productLink, customerLink, bondsmanLInk, caseCSJLink, analystLink)
    }
}

