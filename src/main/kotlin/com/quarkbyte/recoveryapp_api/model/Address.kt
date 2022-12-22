package com.quarkbyte.recoveryapp_api.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Address(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var street: String,
    var city: String,
    var state: String,
    var country: String,
    var postalCode: String,
    var complement: String
)
