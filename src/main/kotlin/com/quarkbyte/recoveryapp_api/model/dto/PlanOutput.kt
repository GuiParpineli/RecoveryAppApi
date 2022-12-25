package com.quarkbyte.recoveryapp_api.model.dto

import java.time.LocalDateTime
import java.util.UUID

data class PlanOutput(
    val id: UUID,
    var value: Double?,
    val initialDate: LocalDateTime?,
    val finalDate: LocalDateTime?,
    var planStatus: Boolean,
    val creationDate: LocalDateTime
)
