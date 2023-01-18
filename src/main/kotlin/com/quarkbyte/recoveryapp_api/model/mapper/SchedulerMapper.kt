package com.quarkbyte.recoveryapp_api.model.mapper

import com.quarkbyte.recoveryapp_api.controller.PlanController
import com.quarkbyte.recoveryapp_api.exceptions.ResourceNotFoundException
import com.quarkbyte.recoveryapp_api.model.dto.SchedulerDTO
import com.quarkbyte.recoveryapp_api.model.dto.TasksDTO
import com.quarkbyte.recoveryapp_api.model.user.Scheduler
import com.quarkbyte.recoveryapp_api.model.user.SchedulerOutput
import com.quarkbyte.recoveryapp_api.model.user.Tasks
import com.quarkbyte.recoveryapp_api.repository.TasksRepository
import com.quarkbyte.recoveryapp_api.repository.UserRepository
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.stereotype.Component

@Component
class SchedulerMapper(
    val tasksRepository: TasksRepository,
    val userRepository: UserRepository
) {
    fun map(input: SchedulerDTO): Scheduler {
        val tasks = mutableListOf<Tasks>()
        for (i in input.tasksID.indices) {
            tasks.add(
                tasksRepository.findById(input.tasksID[i]).orElseThrow {
                    ResourceNotFoundException("None tasks founded")
                }
            )
        }
        val user = userRepository.findById(input.user).orElseThrow { ResourceNotFoundException("None Users founded") }

        return Scheduler(
            user = user,
            task = tasks
        )
    }


    fun map(scheduler: Scheduler): SchedulerOutput {
        val taksdtoList: MutableList<TasksDTO> = mutableListOf()

        scheduler.task.forEach { s ->
            if (s.plan != null) {
                for (i in (s.plan.caseCSJ.indices) - 1)
                    taksdtoList.add(
                        TasksDTO(
                            s.id,
                            s.title,
                            s.plan.code.toString(),
                            "${s.plan.customer.name}  ${s.plan.customer.lastName}",
                            s.plan.caseCSJ[i]!!.typeCaseCSJ,
                            s.plan.analyst.username,
                            s.initialDate,
                            s.note
                        )
                    )
            }
            else {
                taksdtoList.add(
                    TasksDTO(
                        s.id,
                        s.title,
                        "",
                        "",
                        "",
                        "",
                        s.initialDate,
                        s.note
                    )
                )
            }
        }

        return SchedulerOutput(
            id = scheduler.id!!,
            userId = scheduler.user.id!!,
            userName = scheduler.user.name.toString(),
            tasks = taksdtoList
        )
    }

    fun buildSchedulerOutput(scheduler: Scheduler, output: SchedulerOutput): EntityModel<SchedulerOutput> {

        val caseList = mutableListOf<String>()
        val customer = mutableListOf<String>()
        val notes = mutableListOf<String>()

        scheduler.task.forEach { tasks ->
            if (tasks.plan != null)
                caseList.add(tasks.plan.id.toString()) &&
                        customer.add(tasks.plan.customer.id.toString()) &&
                        notes.add(tasks.note.toString())
        }

        val caselistlink = WebMvcLinkBuilder.linkTo(PlanController::class.java)
            .slash("/allbyid?id=${caseList.joinToString(",")}")
            .withRel("PLAN")

        return EntityModel.of(output, caselistlink)
    }

}