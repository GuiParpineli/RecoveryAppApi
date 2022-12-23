package com.quarkbyte.recoveryapp_api.model.customer

import com.quarkbyte.recoveryapp_api.model.customer.Address
import com.quarkbyte.recoveryapp_api.model.enums.Gender
import jakarta.persistence.*
import java.util.*

@Entity
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,
    val name: String? = "",
    val lastName: String? = "",
    val cpf: String? = "",
    val phone: String? = "",
    @ManyToOne
    val address: Address? = null,
    val email: String? = "",
    val birthDay: Date? = null,
    @Enumerated(EnumType.STRING)
    val gender: Gender? = null,
    val nationality: String? = "",
)

