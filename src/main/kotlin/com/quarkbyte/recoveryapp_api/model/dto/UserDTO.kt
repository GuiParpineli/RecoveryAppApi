package com.quarkbyte.recoveryapp_api.model.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.quarkbyte.recoveryapp_api.model.user.UserApp
import java.util.UUID

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserDTO(
    val id: UUID?,
    val name: String,
    val lastname: String,
    val jwt: String? = null
) {
}
