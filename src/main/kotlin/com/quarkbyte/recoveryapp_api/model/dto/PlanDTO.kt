package com.quarkbyte.recoveryapp_api.model.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.UUID

@JsonIgnoreProperties(ignoreUnknown = true)
data class PlanDTO(
    var value: Double,
    var planStatus: Boolean,
    val analystId : UUID,
    var productListId: List<UUID>,
    var customerId: UUID,
    var bondsmanId: UUID,
    var caseCSJId: UUID
)

