package com.quarkbyte.recoveryapp_api.model.mapper

import com.quarkbyte.recoveryapp_api.model.dto.TaskInput
import com.quarkbyte.recoveryapp_api.model.plan.Plan
import com.quarkbyte.recoveryapp_api.model.user.Tasks
import com.quarkbyte.recoveryapp_api.repository.PlanRepository
import com.quarkbyte.recoveryapp_api.repository.TasksRepository
import org.springframework.stereotype.Component

@Component
class TasksMapper(
    private val tasksRepository: TasksRepository,
    private val planRepository: PlanRepository
) {
    fun map(input: TaskInput): Tasks {
        var plan: Plan? = null
        if (input.planCode != null) {
            plan = planRepository.findPlanByCode(input.planCode.toString())
        }

        return Tasks(
            id = input.id,
            title = input.title,
            plan = plan,
            initialDate = input.initialDate,
            note = input.note
        )
    }
}