package com.quarkbyte.recoveryapp_api.model.plan

import com.quarkbyte.recoveryapp_api.model.cases.CaseRecovery
import com.quarkbyte.recoveryapp_api.model.customer.Bondsman
import com.quarkbyte.recoveryapp_api.model.customer.Customer
import com.quarkbyte.recoveryapp_api.model.dto.BondsmanDTO
import com.quarkbyte.recoveryapp_api.model.dto.CustomerDTO
import com.quarkbyte.recoveryapp_api.model.dto.PlanDTOs
import com.quarkbyte.recoveryapp_api.model.dto.UserDTO
import com.quarkbyte.recoveryapp_api.model.user.UserApp
import jakarta.persistence.*
import java.util.*

@Entity
@Cacheable
open class Plan(

    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: UUID? = null,

    val planStatus: Boolean,

    val code: String,

    var value: Double,

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "analyst_id") val analyst: UserApp,

    val initialDate: Date,

    val finalDate: Date? = null,

    @ManyToMany @JoinTable(
        name = "plan_products", joinColumns = [JoinColumn(name = "id")],
        inverseJoinColumns = [JoinColumn(name = "id_products")]
    ) val productList: List<Product>,

    @OneToOne @JoinColumn(name = "customer_id") val customer: Customer,

    @OneToOne @JoinColumn(name = "bondsman_id") val bondsman: Bondsman,

    @ManyToMany @JoinTable(
        joinColumns = [JoinColumn(name = "plan_id")],
        inverseJoinColumns = [JoinColumn(name = "caseCSJ_id", unique = true)]
    ) val caseRecovery: List<CaseRecovery?>,

    ) {
    var recidivistCustomer: Boolean? = false

    val creationDate: Date = Date()

    fun convertDTO(plan: Plan): PlanDTOs {
        val userDTO =  UserDTO(
            plan.analyst.id,
            plan.analyst.name,
            plan.analyst.lastname,
            null
        )
        val customerDTO =CustomerDTO(
            plan.customer.id!!,
            plan.customer.name,
            plan.customer.lastName,
            plan.customer.phone,
            plan.customer.email,
            plan.customer.address
        )
        val bondsmanDTO = BondsmanDTO(
            plan.bondsman.id!!,
            plan.bondsman.name,
            plan.bondsman.lastName,
            plan.bondsman.phone,
            plan.bondsman.email,
            plan.bondsman.address
        )
        return (
                PlanDTOs(
                    id = plan.id,
                    planStatus = plan.planStatus,
                    code = plan.code,
                    value = plan.value,
                    user = userDTO,
                    initialDate = plan.initialDate,
                    finalDate = plan.finalDate,
                    productList = plan.productList,
                    customer = customerDTO,
                    bondsman = bondsmanDTO,
                    caseRecovery = plan.caseRecovery,
                    recidivistCustomer = plan.recidivistCustomer,
                    creationDate = plan.creationDate
                )
                )
    }
}

