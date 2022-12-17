package com.quarkbyte.recoveryapp_api.model

import com.quarkbyte.recoveryapp_api.model.enums.Gender
import jakarta.persistence.*

@Entity
data class Bondsman(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String,
    var lastname: String,
    var cpf: String,
    var phone: String,
    @OneToOne
    var address: Address,
    var email: String,
    var birthDay: String,
    @Enumerated(EnumType.STRING)
    var gender: Gender?,
    var nationality: String
)