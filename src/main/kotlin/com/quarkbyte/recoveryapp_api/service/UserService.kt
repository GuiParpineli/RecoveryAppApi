package com.quarkbyte.recoveryapp_api.service

import com.quarkbyte.recoveryapp_api.exceptions.ResourceNotFoundException
import com.quarkbyte.recoveryapp_api.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(val repository: UserRepository) {
    fun getAll(): ResponseEntity<*> {
        val saved = repository.findAll()
        return ResponseEntity.ok(saved)
    }

    @Throws(ResourceNotFoundException::class)
    fun getById(id: UUID): ResponseEntity<*> {
        val saved = repository.findById(id).orElseThrow { ResourceNotFoundException("None users founded") }
        return ResponseEntity.ok(saved)
    }
}