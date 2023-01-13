package com.quarkbyte.recoveryapp_api.model.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.quarkbyte.recoveryapp_api.model.customer.Address
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class CustomerDTO(
    val id: UUID,
    val name: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val address: Address
)
