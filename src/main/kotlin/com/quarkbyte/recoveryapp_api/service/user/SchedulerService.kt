package com.quarkbyte.recoveryapp_api.service.user

import com.quarkbyte.recoveryapp_api.exceptions.ResourceNotFoundException
import com.quarkbyte.recoveryapp_api.model.dto.SchedulerDTO
import com.quarkbyte.recoveryapp_api.model.mapper.SchedulerMapper
import com.quarkbyte.recoveryapp_api.model.user.Scheduler
import com.quarkbyte.recoveryapp_api.model.user.SchedulerOutput
import com.quarkbyte.recoveryapp_api.model.user.Tasks
import com.quarkbyte.recoveryapp_api.repository.ScheduleRepository
import com.quarkbyte.recoveryapp_api.repository.TasksRepository
import com.quarkbyte.recoveryapp_api.repository.UserRepository
import com.quarkbyte.recoveryapp_api.service.casecsj.SinistroService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.hateoas.EntityModel
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class SchedulerService(
    val repository: ScheduleRepository,
    val mapper: SchedulerMapper,
    val userRepository: UserRepository,
    val taskRepository: TasksRepository
) {

    fun getAll(): ResponseEntity<*> {
        val founded = repository.findAll()
        val output: MutableList<SchedulerOutput> = mutableListOf()
        founded.forEach { output.add(mapper.map(it)) }
        val saved: MutableList<EntityModel<SchedulerOutput>> = mutableListOf()
        for (s in founded.indices)
            saved.add(mapper.buildSchedulerOutput(founded[s], output[s]))
        return ResponseEntity.ok(saved)
    }


    fun getByUserId(id: UUID): ResponseEntity<*> {
        val user = userRepository.findById(id).orElseThrow { ResourceNotFoundException("None users founded") }
        val founded = repository.findByUser(user)
        val output: SchedulerOutput = mapper.map(founded!!)
        val returned = mapper.buildSchedulerOutput(founded, output)
        return ResponseEntity.ok(returned)
    }

    fun save(scheduler: SchedulerDTO): ResponseEntity<*> {
        val saved: Scheduler
        val user =
            userRepository.findById(scheduler.user).orElseThrow {
                ResourceNotFoundException("None users founded")
            }
        val schedulerSave = mapper.map(scheduler)
        val founded = repository.findByUser(user)
        saved = if (founded !== null) {
            schedulerSave.task.forEach { founded.task.add(it) }
            repository.saveAndFlush(founded)
        } else {
            repository.save(schedulerSave)
        }
        return ResponseEntity.ok(saved)
    }

    private val logger: Logger = LoggerFactory.getLogger(SinistroService::class.java)

    fun deleteTask(taskId: UUID, schedulerId: UUID): ResponseEntity<*> {
        val scheduler =
            repository.findById(schedulerId).orElseThrow { ResourceNotFoundException("None schedulers founded") }
        val saved = taskRepository.findById(taskId).orElseThrow { ResourceNotFoundException("None tasks founded") }

        scheduler.task.remove(saved)
        logger.info(scheduler.task.toString())

        taskRepository.deleteById(taskId)

        return ResponseEntity.ok("Success")
    }

}