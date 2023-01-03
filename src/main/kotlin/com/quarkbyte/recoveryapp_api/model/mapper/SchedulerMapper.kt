package com.quarkbyte.recoveryapp_api.model.mapper

import com.quarkbyte.recoveryapp_api.controller.CaseController
import com.quarkbyte.recoveryapp_api.controller.CustomerController
import com.quarkbyte.recoveryapp_api.controller.PlanController
import com.quarkbyte.recoveryapp_api.controller.UserController
import com.quarkbyte.recoveryapp_api.exceptions.ResourceNotFoundException
import com.quarkbyte.recoveryapp_api.model.dto.SchedulerDTO
import com.quarkbyte.recoveryapp_api.model.dto.TasksDTO
import com.quarkbyte.recoveryapp_api.model.user.Scheduler
import com.quarkbyte.recoveryapp_api.model.user.SchedulerOutput
import com.quarkbyte.recoveryapp_api.model.user.Tasks
import com.quarkbyte.recoveryapp_api.repository.TasksRepository
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.stereotype.Component

@Component
class SchedulerMapper(
    val tasksRepository: TasksRepository
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
        return Scheduler(
            task = tasks
        )
    }


    fun map(scheduler: Scheduler): SchedulerOutput {
        val taksdtoList: MutableList<TasksDTO> = mutableListOf()
        scheduler.task.forEach { s ->
            taksdtoList.add(
                TasksDTO(
                    s.plan.code,
                    s.plan.customer.name,
                    s.plan.caseCSJ[0]!!.typeCaseCSJ,
                    s.plan.analyst.username,
                    s.day,
                    s.note
                )
            )
        }
        return SchedulerOutput(
            id = scheduler.id!!,
            tasks = taksdtoList
        )
    }

    fun buildSchedulerOutput(scheduler: Scheduler, output: SchedulerOutput): EntityModel<SchedulerOutput> {

        val caseList = mutableListOf<String>()
        val user = mutableListOf<String>()
        val customer = mutableListOf<String>()
        val notes = mutableListOf<String>()

        scheduler.task.forEach { tasks ->
            caseList.add(tasks.plan.id.toString()) &&
                    user.add(tasks.plan.analyst.id.toString()) &&
                    customer.add(tasks.plan.customer.id.toString()) &&
                    notes.add(tasks.note.toString())
        }

        val caselistlink = WebMvcLinkBuilder.linkTo(PlanController::class.java)
            .slash("?id=${caseList.joinToString(",")}")
            .withRel("PLAN")
            .withTitle(scheduler.task[0].plan.customer.name)
        val userLink = WebMvcLinkBuilder.linkTo(UserController::class.java)
            .slash("?id=${caseList.joinToString(",")}")
            .withRel("Analista")
            .withTitle(user.toString())
        val customerLink = WebMvcLinkBuilder.linkTo(CustomerController::class.java)
            .slash("?id=${customer.joinToString(",")}")
            .withRel("customer")
            .withTitle(customer.toString())

        return EntityModel.of(output, caselistlink, userLink, customerLink)
    }

}