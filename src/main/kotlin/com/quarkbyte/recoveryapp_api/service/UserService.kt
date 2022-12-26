package com.quarkbyte.recoveryapp_api.service

import com.quarkbyte.recoveryapp_api.exceptions.ResourceNotFoundException
import com.quarkbyte.recoveryapp_api.exceptions.SaveErrorException
import com.quarkbyte.recoveryapp_api.model.UserApp
import com.quarkbyte.recoveryapp_api.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(val repository: UserRepository) {
    @Throws(ResourceNotFoundException::class)
    fun getAll(): ResponseEntity<*> {
        val saved = repository.findAll()
        if (saved.isEmpty()) throw ResourceNotFoundException("none users founded")
        return ResponseEntity.ok(saved)
    }

    @Throws(ResourceNotFoundException::class)
    fun getById(id: UUID): ResponseEntity<*> {
        val saved = repository.findById(id).orElseThrow { ResourceNotFoundException("None users founded") }
        return ResponseEntity.ok(saved)
    }

    @Throws(SaveErrorException::class)
    fun save(user: UserApp): ResponseEntity<*> {
        val saved: UserApp = try {
            repository.save(user)
        } catch (e: Exception) {
            throw SaveErrorException("Error, not saved")
        }
        return ResponseEntity.ok<Any>(saved)
    }

    @Throws(SaveErrorException::class)
    fun update(user: UserApp): ResponseEntity<*> {
        var saved: UserApp? = null
        try {
            if (repository.findById(user.id!!).isPresent) saved = repository.saveAndFlush(user)
        } catch (e: Exception) {
            throw SaveErrorException("Error, not saved")
        }
        return ResponseEntity.ok(saved)
    }

    @Throws(ResourceNotFoundException::class)
    fun delete(id: UUID): ResponseEntity<*> {
        if (repository.findById(id).isPresent) repository.deleteById(id)
        return ResponseEntity.ok<String>(" deleted successfully")
    }
}