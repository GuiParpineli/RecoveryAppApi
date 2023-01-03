package com.quarkbyte.recoveryapp_api.model.dto

import java.util.*

data class PlanOutput(
    val code: String,
    var value: Double?,
    var recidivistCustomer: Boolean? = false,
    val initialDate: Date?,
    val finalDate: Date?,
    var planStatus: Boolean,
    val creationDate: Date,
)
