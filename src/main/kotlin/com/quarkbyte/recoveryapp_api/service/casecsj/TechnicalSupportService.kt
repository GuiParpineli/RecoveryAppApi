package com.quarkbyte.recoveryapp_api.service.casecsj

import com.quarkbyte.recoveryapp_api.exceptions.ResourceNotFoundException
import com.quarkbyte.recoveryapp_api.exceptions.SaveErrorException
import com.quarkbyte.recoveryapp_api.model.cases.TechnicalSupport
import com.quarkbyte.recoveryapp_api.repository.TechnicalSupportRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class TechnicalSupportService(private val repository: TechnicalSupportRepository) {

    fun get(): ResponseEntity<*> {
        val saved = repository.findAll()
        if(saved.isEmpty()) throw ResourceNotFoundException("None technical founded")
        return ResponseEntity.ok(saved)
    }

    @Throws(ResourceNotFoundException::class)
    fun getById(id: UUID): ResponseEntity<*> {
        val saved = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("None cases founded") }!!
        return ResponseEntity.ok(saved)
    }

    @Throws(SaveErrorException::class)
    fun save(techSupport: TechnicalSupport): ResponseEntity<*> {
        val saved: TechnicalSupport = try {
            repository.save(techSupport)
        } catch (e: Exception) {
            throw SaveErrorException("Error, not saved")
        }
        return ResponseEntity.ok(saved)
    }

    @Throws(SaveErrorException::class)
    fun update(techSupport: TechnicalSupport): ResponseEntity<*> {
        val saved: TechnicalSupport = try {
            repository.saveAndFlush(techSupport)
        } catch (e: Exception) {
            throw SaveErrorException("Error, not saved")
        }
        return ResponseEntity.ok(saved)
    }

    @Throws(ResourceNotFoundException::class)
    fun delete(id: UUID): ResponseEntity<*> {
        if (repository.findById(id).isPresent)
        repository.deleteById(id)
        return ResponseEntity.ok("deleted successfully")
    }
}