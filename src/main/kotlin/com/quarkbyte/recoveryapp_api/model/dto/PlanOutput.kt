package com.quarkbyte.recoveryapp_api.model.dto

import com.quarkbyte.recoveryapp_api.model.cases.CaseCSJ
import java.time.LocalDateTime

data class PlanOutput(
    val code: String,
    var value: Double?,
    var recidivistCustomer: Boolean? = false,
    val initialDate: LocalDateTime?,
    val finalDate: LocalDateTime?,
    var planStatus: Boolean,
    var caseCSJ: List<CaseCSJ?>,
    val creationDate: LocalDateTime,
)
