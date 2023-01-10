package com.quarkbyte.recoveryapp_api.model.dto

import java.util.*

data class TaskInput(
    val title: String,
    val planCode: String?,
    val initialDate: Date,
    val finalDate: Date,
    val note: MutableMap<Int, String>
)
