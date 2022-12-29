package com.quarkbyte.recoveryapp_api.model.dto

import java.time.LocalDateTime
import java.util.UUID

data class PlanOutput(
    val code: String,
    var value: Double?,
    var recidivistCustomer: Boolean? = false,
    val initialDate: LocalDateTime?,
    val finalDate: LocalDateTime?,
    var planStatus: Boolean,
    val creationDate: LocalDateTime
)
