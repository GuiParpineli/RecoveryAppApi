package com.quarkbyte.recoveryapp_api.service.user

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import com.quarkbyte.recoveryapp_api.exceptions.ResourceNotFoundException
import com.quarkbyte.recoveryapp_api.exceptions.SaveErrorException
import com.quarkbyte.recoveryapp_api.model.dto.UserDTO
import com.quarkbyte.recoveryapp_api.model.user.UserApp
import com.quarkbyte.recoveryapp_api.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    val repository: UserRepository,
    val mapper: ObjectMapper
) : UserDetailsService {
    @Throws(ResourceNotFoundException::class)
    fun getAll(): ResponseEntity<*> {
        val saved = repository.findAll()
        if (saved.isEmpty()) throw ResourceNotFoundException("none users founded")
        val userDTOlist = mutableListOf<UserDTO>()
        saved.forEach { u -> userDTOlist.add(mapper.convertValue(u)) }
        return ResponseEntity.ok(userDTOlist)
    }

    @Throws(ResourceNotFoundException::class)
    fun getById(id: UUID): ResponseEntity<*> {
        val saved = repository.findById(id).orElseThrow { ResourceNotFoundException("None users founded") }
        val userDTO: UserDTO = mapper.convertValue(saved)
        return ResponseEntity.ok(userDTO)
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

    override fun loadUserByUsername(username: String): UserDetails = repository.findByUsername(username!!)
}