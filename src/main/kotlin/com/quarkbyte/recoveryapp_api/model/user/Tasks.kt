package com.quarkbyte.recoveryapp_api.model.user

import com.quarkbyte.recoveryapp_api.model.plan.Plan
import jakarta.persistence.*
import java.util.*

@Entity
class Tasks(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,
    val title: String,
    @ManyToOne(fetch = FetchType.LAZY)
    val plan: Plan?,
    val initialDate: Date,
    val note: String
)
