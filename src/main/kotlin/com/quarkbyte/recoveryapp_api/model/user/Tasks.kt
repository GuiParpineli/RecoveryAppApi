package com.quarkbyte.recoveryapp_api.model.user

import com.quarkbyte.recoveryapp_api.model.cases.CaseCSJ
import jakarta.persistence.*
import java.util.*

@Entity
class Tasks(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,
    @ManyToOne
    val csj: CaseCSJ,
    val day: Date,
    @ElementCollection
    val note: MutableMap<Int, String>
)
