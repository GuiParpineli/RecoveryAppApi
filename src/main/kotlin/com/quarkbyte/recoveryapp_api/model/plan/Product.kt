package com.quarkbyte.recoveryapp_api.model.plan

import com.quarkbyte.recoveryapp_api.model.enums.StatusProduct
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.*

@Entity
class Product(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: UUID?,
    val name: String?,
    val statusProduct: StatusProduct?,
    val imei: String?,
    val imei2: String?,
    val value: Double?
)
