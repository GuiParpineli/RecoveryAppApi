package com.quarkbyte.recoveryapp_api.model.dto

import java.util.*

data class TaskInput(
    val id: UUID?,
    val title: String,
    val planCode: String?,
    val initialDate: Date,
    val note: String
)
