package com.quarkbyte.recoveryapp_api.service.user

import com.quarkbyte.recoveryapp_api.model.user.Scheduler
import com.quarkbyte.recoveryapp_api.repository.ScheduleRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class SchedulerService(val repository: ScheduleRepository) {

    fun getall(): ResponseEntity<*> {
        val founded = repository.findAll()
        return ResponseEntity.ok(founded)
    }

    fun save(scheduler: Scheduler): ResponseEntity<*> {
        val saved = repository.save(scheduler)
        return ResponseEntity.ok(saved)
    }

}