package com.quarkbyte.recoveryapp_api.service.user

import com.quarkbyte.recoveryapp_api.model.dto.SchedulerDTO
import com.quarkbyte.recoveryapp_api.model.mapper.SchedulerMapper
import com.quarkbyte.recoveryapp_api.model.user.Scheduler
import com.quarkbyte.recoveryapp_api.model.user.SchedulerOutput
import com.quarkbyte.recoveryapp_api.repository.ScheduleRepository
import org.springframework.hateoas.EntityModel
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class SchedulerService(
    val repository: ScheduleRepository,
    val mapper: SchedulerMapper
) {

    fun getAll(): ResponseEntity<*> {
        val founded = repository.findAll()
        val output: MutableList<SchedulerOutput> = mutableListOf()
        founded.forEach { f -> output.add(mapper.map(f)) }
        val saved: MutableList<EntityModel<SchedulerOutput>> = mutableListOf()
        for (s in founded.indices)
            saved.add(mapper.buildSchedulerOutput(founded[s], output[s]))
        return ResponseEntity.ok(saved)
    }



    fun save(scheduler: SchedulerDTO): ResponseEntity<*> {
        val schedulerSave = mapper.map(scheduler)
        val saved = repository.save(schedulerSave)
        return ResponseEntity.ok(saved)
    }

}