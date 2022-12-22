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
    private val id: UUID? = null,
    private val value: Double? = null,
    private val planStatus: Boolean? = null,

    @ManyToOne
    @JoinColumn(name = "analyst_id")
    private val analyst: UserApp? = null,
    private val initialDate: LocalDateTime? = null,
    private val finalDate: LocalDateTime? = null,

    @ManyToMany
    @JoinTable(
        name = "plan_products",
        joinColumns = [JoinColumn(name = "id")],
        inverseJoinColumns = [JoinColumn(name = "id_products")]
    )
    private val productList: List<Product>? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private val customer: Customer? = null,

    @ManyToOne
    @JoinColumn(name = "bondsman_id")
    private val bondsman: Bondsman? = null,

    @ManyToOne
    @JoinColumn(name = "caseCSJ_id")
    private val caseCSJ: CaseCSJ? = null,

    ) {
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private val createDate: String? = LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))
}

