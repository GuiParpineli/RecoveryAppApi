package com.quarkbyte.recoveryapp_api.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.UUID

@Entity
class UserApp(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: UUID? = null,
    val name: String?,
)