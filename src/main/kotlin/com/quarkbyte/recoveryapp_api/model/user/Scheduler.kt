package com.quarkbyte.recoveryapp_api.model.user

import jakarta.persistence.CollectionTable
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import java.util.UUID

@Entity
open class Scheduler(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: UUID? = null,
    @ManyToOne
    val user: UserApp,
    @ManyToMany
    val task: MutableList<Tasks> = mutableListOf(),
)