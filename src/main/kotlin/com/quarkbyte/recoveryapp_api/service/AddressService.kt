package com.quarkbyte.recoveryapp_api.service

import com.quarkbyte.recoveryapp_api.exceptions.ResourceNotFoundException
import com.quarkbyte.recoveryapp_api.exceptions.SaveErrorException
import com.quarkbyte.recoveryapp_api.model.Address
import com.quarkbyte.recoveryapp_api.repository.AddressRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class AddressService(val repository: AddressRepository){

    @Throws(ResourceNotFoundException::class)
    fun get(): ResponseEntity<*> {
        val saved = repository.findAll()
        if (saved.isEmpty()) throw ResourceNotFoundException("None Address founded")
        return ResponseEntity.ok(saved)
    }

    @Throws(ResourceNotFoundException::class)
    fun getById(id: UUID): ResponseEntity<*> {
        val saved = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("None address founded") }!!
        return ResponseEntity.ok(saved)
    }

    @Throws(SaveErrorException::class)
    fun save(address: Address): ResponseEntity<*> {
        val saved: Address = try {
            repository.save(address)
        } catch (e: Exception) {
            throw SaveErrorException("Error, not saved")
        }
        return ResponseEntity.ok(saved)
    }

    @Throws(SaveErrorException::class)
    fun update(address: Address): ResponseEntity<*> {
        val saved: Address = try {
            repository.saveAndFlush(address)
        } catch (e: Exception) {
            throw SaveErrorException("Error, not saved")
        }
        return ResponseEntity.ok(saved)
    }

    @Throws(ResourceNotFoundException::class)
    fun delete(id: UUID): ResponseEntity<*> {
        val saved = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("None address founded") }!!
        repository.deleteById(id)
        return ResponseEntity.ok("Address$saved deleted successfully")
    }
}
