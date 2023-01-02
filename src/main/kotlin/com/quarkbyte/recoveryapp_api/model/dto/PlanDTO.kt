package com.quarkbyte.recoveryapp_api.model.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class PlanDTO(
    val code: String,
    var value: Double,
    var planStatus: Boolean,
    val initialDate: Date,
    val finalDate: Date?,
    val analystId: UUID,
    var productListId: List<UUID>,
    var customerId: UUID,
    var bondsmanId: UUID,
    var caseCSJId: List<UUID>
)

