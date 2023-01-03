package com.quarkbyte.recoveryapp_api.model.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.quarkbyte.recoveryapp_api.model.enums.SystemUserRoles
import java.util.UUID

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserDTO(
    val id: UUID?,
    val name: String,
    val systemUserRoles: SystemUserRoles
)
