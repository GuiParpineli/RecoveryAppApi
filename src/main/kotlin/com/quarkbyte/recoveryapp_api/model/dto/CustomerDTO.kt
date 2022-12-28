package com.quarkbyte.recoveryapp_api.model.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.quarkbyte.recoveryapp_api.model.customer.Address

@JsonIgnoreProperties(ignoreUnknown = true)
data class CustomerDTO(
    val name: String? ,
    val lastName: String? ,
    val email: String? ,
    val address: Address
)
