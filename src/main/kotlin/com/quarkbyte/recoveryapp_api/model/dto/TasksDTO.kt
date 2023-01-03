package com.quarkbyte.recoveryapp_api.model.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class TasksDTO(
    val planCode: String,
    val planCustomer: String,
    val planCaseType: String,
    val analyst: String,
    val day: Date,
    val note: MutableMap<Int, String>
)
