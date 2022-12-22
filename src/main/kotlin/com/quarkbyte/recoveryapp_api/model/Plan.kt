package com.quarkbyte.recoveryapp_api.model

import com.quarkbyte.recoveryapp_api.model.cases.CaseCSJ
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Entity
data class Plan(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,
    val value: Double? = null,
    val planStatus: Boolean? = null,

    @ManyToOne
    @JoinColumn(name = "analyst_id")
    val analyst: UserApp? = null,
    val initialDate: LocalDateTime? = null,
    val finalDate: LocalDateTime? = null,

    @ManyToMany
    @JoinTable(
        name = "plan_products",
        joinColumns = [JoinColumn(name = "id")],
        inverseJoinColumns = [JoinColumn(name = "id_products")]
    )
    val productList: List<Product>? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    val customer: Customer? = null,

    @ManyToOne
    @JoinColumn(name = "bondsman_id")
    val bondsman: Bondsman? = null,

    @ManyToOne
    @JoinColumn(name = "caseCSJ_id")
    val caseCSJ: CaseCSJ? = null,

    ) {
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    val createDate: String? = LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))
}

