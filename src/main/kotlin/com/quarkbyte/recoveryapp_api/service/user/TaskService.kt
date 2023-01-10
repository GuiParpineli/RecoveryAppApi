package com.quarkbyte.recoveryapp_api.service.user

import com.quarkbyte.recoveryapp_api.model.dto.TaskInput
import com.quarkbyte.recoveryapp_api.model.mapper.TasksMapper
import com.quarkbyte.recoveryapp_api.model.user.Tasks
import com.quarkbyte.recoveryapp_api.repository.TasksRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class TaskService(
    private val repository: TasksRepository,
    private val mapper: TasksMapper
) {
    fun getAll(): ResponseEntity<*> {
        val founded = repository.findAll()
        return ResponseEntity.ok(founded)
    }

    fun save(tasks: TaskInput): ResponseEntity<*> {
        val task = mapper.map(tasks)
        val saved = repository.save(task)
        return ResponseEntity.ok(saved)
    }
}