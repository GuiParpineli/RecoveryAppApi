package com.quarkbyte.recoveryapp_api.model.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BondsmanDTO(
    var name: String? = null,
    var lastName: String? = null,
    var email: String? = null,
)
