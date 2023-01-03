package com.quarkbyte.recoveryapp_api.model.user

import com.quarkbyte.recoveryapp_api.model.dto.TasksDTO
import java.util.UUID

data class SchedulerOutput(
    val id: UUID,
    val tasks: List<TasksDTO>
)
