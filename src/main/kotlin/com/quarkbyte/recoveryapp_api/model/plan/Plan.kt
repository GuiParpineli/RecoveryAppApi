package com.quarkbyte.recoveryapp_api.model.plan

import com.quarkbyte.recoveryapp_api.model.UserApp
import com.quarkbyte.recoveryapp_api.model.cases.CaseCSJ
import com.quarkbyte.recoveryapp_api.model.customer.Bondsman
import com.quarkbyte.recoveryapp_api.model.customer.Customer
import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.util.*

@Entity
@Cacheable
open class Plan(

    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: UUID? = null,

    val planStatus: Boolean,

    val code: String,

    var value: Double,

    @ManyToOne @JoinColumn(name = "analyst_id") val analyst: UserApp,

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
    ) val caseCSJ: List<CaseCSJ?>,

    ) {
    var recidivistCustomer: Boolean? = false

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    val creationDate: LocalDateTime = LocalDateTime.now()
}

