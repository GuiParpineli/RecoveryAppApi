package com.quarkbyte.recoveryapp_api.model

import com.quarkbyte.recoveryapp_api.model.enums.Gender
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.Date

@Entity
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String,
    var lastName: String,
    var cpf: String,
    var phone: String,
    var email: String,
    var date: Date,
    var gender: Gender,
    var nationality: String

)

