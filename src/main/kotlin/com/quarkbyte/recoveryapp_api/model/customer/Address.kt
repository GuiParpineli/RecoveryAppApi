package com.quarkbyte.recoveryapp_api.model.customer

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Address(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var street: String,
    var city: String,
    var state: String,
    var country: String,
    var postalCode: String,
    var complement: String?
)
