package com.quarkbyte.recoveryapp_api.model

import com.quarkbyte.recoveryapp_api.model.enums.PlanStatus
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
import java.util.Date

@Entity
data class Plan(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var value: Double,
    var planStatus: PlanStatus?,
    var initialDate: Date?,
    var finalDate: Date?,
    @ManyToMany
    @JoinTable(
        name = "plan_products",
        joinColumns = [JoinColumn(name = "id")],
        inverseJoinColumns = [JoinColumn(name = "id_product")]
    )
    var products: List<Product>,

    @ManyToOne
    @JoinColumn(name = "id_bondsman")
    var bondsman: Bondsman,
    @ManyToOne
    @JoinColumn(name = "id_caseCJS")
    var caseCSJ: CaseCJS?,
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    var createDate: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.YYYY HH:mm:ss"))
)
