package com.quarkbyte.recoveryapp_api.model.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class TasksDTO(
    val title: String,
    val planCode: String,
    val planCustomer: String,
    val planCaseType: String,
    val analyst: String,
    val initialDay: Date,
    val finalDay: Date,
    val note: String
)
