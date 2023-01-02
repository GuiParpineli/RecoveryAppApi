package com.quarkbyte.recoveryapp_api.model.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.quarkbyte.recoveryapp_api.model.plan.Product
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
class PlanInput (
    val id: UUID,
    val code: String,
    var value: Double,
    var planStatus: Boolean,
    var productList: List<Product>?,
    val finalDate: Date?,
)

