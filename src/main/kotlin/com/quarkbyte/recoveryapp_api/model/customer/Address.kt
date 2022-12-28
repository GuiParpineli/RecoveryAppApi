package com.quarkbyte.recoveryapp_api.model.customer

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.NoArgsConstructor
import java.util.UUID

@Entity
class Address{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
    var street: String = ""
    var city: String = ""
    var state: String = ""
    var country: String = ""
    var postalCode: String = ""
    var complement: String? = null

    constructor(street: String, city: String,
                state: String, country: String,
                postalCode: String,
                complement: String?) {
        this.street = street
        this.city = city
        this.state = state
        this.country = country
        this.postalCode = postalCode
        this.complement = complement
    }
    constructor( )

}
