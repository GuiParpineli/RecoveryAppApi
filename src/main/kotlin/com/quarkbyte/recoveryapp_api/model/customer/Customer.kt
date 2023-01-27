package com.quarkbyte.recoveryapp_api.model.customer

import com.quarkbyte.recoveryapp_api.model.customer.Address
import com.quarkbyte.recoveryapp_api.model.enums.Gender
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.UniqueElements
import java.util.*

@Entity
class Customer(

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,

    @NotNull @Size(min = 3, max = 20)
    val name: String,

    @NotNull @Size(min = 3, max = 30)
    val lastName: String,

    @NotNull @Size(min = 12) @Column(unique = true)
    val cpf: String,

    @NotNull @Column(unique = true)
    val phone: String,

    @ManyToOne
    val address: Address,

    @NotNull @Size(min = 10, max = 40)
    val email: String,

    @NotNull
    val birthDay: Date,

    @Enumerated(EnumType.STRING)
    val gender: Gender?,

    @NotNull
    val nationality: String
)

