package com.quarkbyte.recoveryapp_api.model

import com.quarkbyte.recoveryapp_api.model.enums.Gender
import jakarta.persistence.*
import java.util.*

@Entity
data class Bondsman(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,
    val name: String? = null,
    val lastName: String? = null,
    val cpf: String? = null,
    val phone: String? = null,
    @ManyToOne
    val address: Address? = null,
    val email: String? = null,
    val birthDay: Date? = null,
    @Enumerated(EnumType.STRING)
    val gender: Gender? = null,
    val nationality: String? = null,
)