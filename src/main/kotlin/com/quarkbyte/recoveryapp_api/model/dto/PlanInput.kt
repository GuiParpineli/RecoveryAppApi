package com.quarkbyte.recoveryapp_api.model.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDateTime
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
class PlanInput (
    val id: UUID,
    var value: Double,
    var planStatus: Boolean,
    val finalDate: LocalDateTime?,
)

