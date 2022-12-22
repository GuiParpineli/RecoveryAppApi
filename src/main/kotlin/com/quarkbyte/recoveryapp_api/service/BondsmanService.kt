package com.quarkbyte.recoveryapp_api.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import com.quarkbyte.recoveryapp_api.exceptions.ResourceNotFoundException
import com.quarkbyte.recoveryapp_api.exceptions.SaveErrorException
import com.quarkbyte.recoveryapp_api.model.Bondsman
import com.quarkbyte.recoveryapp_api.model.dto.BondsmanDTO
import com.quarkbyte.recoveryapp_api.repository.BondsmanRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Consumer

@Service
class BondsmanService(
    val repository: BondsmanRepository,
    val mapper: ObjectMapper
) {

    @Throws(ResourceNotFoundException::class)
    fun get(): ResponseEntity<*> {
        val saved = repository.findAll()
        val bondsmanDTOS: MutableList<BondsmanDTO> = ArrayList()
        if (saved.isEmpty()) throw ResourceNotFoundException("None Bondsman founded")
        saved.forEach { c  -> bondsmanDTOS.add(mapper.convertValue(c)) }
        return ResponseEntity.ok(saved)
    }

    @Throws(ResourceNotFoundException::class)
    fun getById(id: UUID): ResponseEntity<*> {
        val saved = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("None address founded") }!!
        return ResponseEntity.ok(saved)
    }

    @Throws(SaveErrorException::class)
    fun save(bondsman: Bondsman): ResponseEntity<*> {
        val saved: Bondsman = try {
            repository.save(bondsman)
        } catch (e: Exception) {
            throw SaveErrorException("Error, not saved")
        }
        return ResponseEntity.ok(saved)
    }

    @Throws(SaveErrorException::class)
    fun update(bondsman: Bondsman): ResponseEntity<*> {
        val saved: Bondsman = try {
            repository.saveAndFlush(bondsman)
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
        return ResponseEntity.ok("$saved deleted successfully")
    }
}