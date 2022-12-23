package com.quarkbyte.recoveryapp_api.model.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.quarkbyte.recoveryapp_api.model.Bondsman
import com.quarkbyte.recoveryapp_api.model.Customer
import com.quarkbyte.recoveryapp_api.model.Product
import com.quarkbyte.recoveryapp_api.model.cases.CaseCSJ
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

