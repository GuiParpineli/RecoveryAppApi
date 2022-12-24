package com.quarkbyte.recoveryapp_api.service.casecsj

import com.quarkbyte.recoveryapp_api.exceptions.ResourceNotFoundException
import com.quarkbyte.recoveryapp_api.exceptions.SaveErrorException
import com.quarkbyte.recoveryapp_api.model.cases.Sinistro
import com.quarkbyte.recoveryapp_api.repository.SinistroRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class SinistroService(private val repository: SinistroRepository) {

    fun get(): ResponseEntity<*> {
        val saved = repository.findAll()
        return ResponseEntity.ok(saved)
    }

    @Throws(ResourceNotFoundException::class)
    fun getById(id: UUID): ResponseEntity<*> {
        val saved = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("None cases founded") }!!
        return ResponseEntity.ok(saved)
    }

    @Throws(SaveErrorException::class)
    fun save(sinistro: Sinistro): ResponseEntity<*> {
        val saved: Sinistro = try {
            repository.save(sinistro)
        } catch (e: Exception) {
            throw SaveErrorException("Error not saved")
        }
        return ResponseEntity.ok(saved)
    }

    @Throws(SaveErrorException::class)
    fun update(sinistro: Sinistro): ResponseEntity<*> {
        val saved: Sinistro = try {
            repository.saveAndFlush(sinistro)
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