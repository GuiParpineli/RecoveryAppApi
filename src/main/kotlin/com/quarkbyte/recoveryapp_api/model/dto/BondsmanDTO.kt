package com.quarkbyte.recoveryapp_api.model.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.quarkbyte.recoveryapp_api.model.customer.Address

@JsonIgnoreProperties(ignoreUnknown = true)
data class BondsmanDTO(
    var name: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    val address: Address
)
