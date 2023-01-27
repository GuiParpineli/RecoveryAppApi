package com.quarkbyte.recoveryapp_api.model.dto

import com.quarkbyte.recoveryapp_api.model.cases.CaseRecovery
import com.quarkbyte.recoveryapp_api.model.plan.Product
import java.util.*

data class PlanOutput(
    val code: String,
    var value: Double?,
    var recidivistCustomer: Boolean? = false,
    val initialDate: Date?,
    val finalDate: Date?,
    var planStatus: Boolean,
    val creationDate: Date,
    val productList: List<Product>,
    val analyst: UserDTO,
    val customer: CustomerDTO,
    val bondsman: BondsmanDTO,
    val caseRecovery: List<CaseRecovery?>
)
