package com.quarkbyte.recoveryapp_api.model

import com.quarkbyte.recoveryapp_api.model.enums.Gender
import jakarta.persistence.*
import java.util.*

@Entity
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: UUID? = null,
    private val name: String? = null,
    private val lastName: String? = null,
    private val cpf: String? = null,
    private val phone: String? = null,
    @ManyToOne
    private val address: Address? = null,
    private val email: String? = null,
    private val birthDay: Date? = null,
    @Enumerated(EnumType.STRING)
    private val gender: Gender? = null,
    private val nationality: String? = null,

    )

