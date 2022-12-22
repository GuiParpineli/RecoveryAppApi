package com.quarkbyte.recoveryapp_api.model

import com.quarkbyte.recoveryapp_api.model.enums.StatusProduct
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.*

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: UUID? = null,
    private val name: String? = null,
    private val statusProduct: StatusProduct? = null,
    private val imei: String? = null,
    private val imei2: String? = null,
    private val value: Double? = null,
)
